package com.pol.entities;

import android.opengl.GLES20;

import com.pol.graphics.textures.Texture;
import com.pol.utils.CnsOpenGL;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public class Sprite extends Shape {
    protected Texture texture;
    protected int mTexture;
    protected int mUV;


    /*
     * CONSTRUCTORS
     */
    @Deprecated //NOT USE
    protected Sprite(float x, float y) {
        super(x, y);
    }

    @Deprecated //NOT USE
    protected Sprite(float x, float y, Texture texture) {
        super(x, y);
        this.texture = texture;
    }

    @Override
    protected void setShader(int idShader) {
        super.setShader(idShader);
        mTexture = GLES20.glGetUniformLocation(mProgram, "myTexture");
        mUV = GLES20.glGetAttribLocation(mProgram, "uv");
    }

    @Override
    public void render(float[] mVPMatrix) {
        if (!visible) return;
        if (scaleX < 0 || scaleY < 0)
            GLES20.glDisable(GLES20.GL_CULL_FACE);

        GLES20.glUseProgram(mProgram);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(
                mPositionHandle, CnsOpenGL.CORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CnsOpenGL.CORDS_PER_VERTEX * 4, vertexBuffer);

        GLES20.glEnableVertexAttribArray(mUV);
        GLES20.glVertexAttribPointer(mUV, CnsOpenGL.UV_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CnsOpenGL.UV_PER_VERTEX * 4, texture.getUvBuffer());


        GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mVPMatrix, 0);


        GLES20.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);


        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //ACTIVE TEXTURE
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getGlTexture());
        GLES20.glUniform1i(mTexture, 0);

        // Draw the square
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, shapeIndex.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mUV);

        int length = entities.size();
        for (int i = 0; i < length; i++) {
            entities.get(i).render(mVPMatrix);
        }
        if (scaleX < 0 || scaleY < 0)
            GLES20.glEnable(GLES20.GL_CULL_FACE);


    }
}
