package com.pol.graphics.textures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.pol.engine.SafeUpdateListener;
import com.pol.gabengine.BaseGabGame;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public class TextureFactory {

    private static BaseGabGame context = null;

    public static void init(BaseGabGame context) {
        TextureFactory.context = context;
    }

    public static Texture LoadTexture(String textureName) {
        Bitmap bitmap = null;
        Texture texture = new Texture();

        try {
            InputStream ims = context.getAssets().open(textureName);
            bitmap = BitmapFactory.decodeStream(ims);
        } catch (IOException e) {
            e.printStackTrace();
        }

        texture.glTexture = loadGLTextureFromBitmap(bitmap);
        texture.width = bitmap.getWidth();
        texture.height = bitmap.getHeight();
        texture.load();
        return texture;
    }


    /**
     * PROTECTED STATIC METHODS
     */
    protected static int loadGLTextureFromBitmap(Bitmap bitmap) {
        int textureId[] = new int[1];
        GLES20.glGenTextures(1, textureId, 0);
        //bind texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
        //FILTER TEXTURE
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
        //NOTHING
        //Use Android to load Texture
        //GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        bitmap.recycle();
        if (textureId[0] == 0) {
            throw new RuntimeException("Error loading texture.");
        }
        Log.i("GabEngine", "TextureLoad! ID:" + textureId[0]);

        return textureId[0];
    }

    protected static void deleteTexture(final int[] texture) {
        context.getEngine().setSafeOnThreadUpdate(new SafeUpdateListener() {
            @Override
            public void onUpdate(float elapsedTime) {
                GLES20.glDeleteTextures(1, texture, 0);
            }
        });

    }


}