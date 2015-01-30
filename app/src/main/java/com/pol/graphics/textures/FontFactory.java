package com.pol.graphics.textures;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 29/01/15.
 */

public class FontFactory {

    public final static int CHAR_START = 32; //START CHAR (32 in ASCII)
    public final static int CHAR_END = 252; //END CHAR (126 in ASCII)
    public final static int CHAR_CNT = (((CHAR_END - CHAR_START) + 1) + 1);  //TOTAL CHAR NUMBER
    public final static int CHAR_UNKNOWN = (CHAR_CNT - 1);
    public final static int CHAR_NONE = 32;
    public final static int FONT_SIZE_MIN = 6;
    public final static int FONT_SIZE_MAX = 180;
    public final static int CHAR_BATCH_SIZE = 100;


    private static Activity context = null;

    /*
     * PUBLIC METHODS
     */

    /**
     * Load a ASCII font and save and return a font object
     *
     * @param fontName The font name
     * @param size     The font size
     * @return the font
     */
    public static Font LoadFont(String fontName, float size) {
        Bitmap bitmap = null;
        int textureSize;
        Font font = new Font();
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(context.getAssets(), fontName);
        } catch (Exception e) {
            Log.e("Font Error", "Font file:" + fontName + " not exist in assets");
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setColor(0xffffffff);
        paint.setTypeface(tf);
        Paint.FontMetrics fm = paint.getFontMetrics();
        font.fontHeight = (float) Math.ceil(Math.abs(fm.bottom) + Math.abs(fm.top));
        font.fontAscent = (float) Math.ceil(Math.abs(fm.ascent));
        font.fontDescent = (float) Math.ceil(Math.abs(fm.descent));

        //GET FONT LETTER WITH
        char[] s = new char[2];
        font.charWidthMax = font.charHeight = 0;
        float[] w = new float[2];
        int cnt = 0;
        for (char c = CHAR_START; c <= CHAR_END; c++) {
            s[0] = c;
            paint.getTextWidths(s, 0, 1, w);
            font.charWidths[cnt] = w[0];
            if (font.charWidths[cnt] > font.charWidthMax)
                font.charWidthMax = font.charWidths[cnt];
            cnt++;
        }
        s[0] = CHAR_NONE;
        paint.getTextWidths(s, 0, 1, w);
        font.charWidths[cnt] = w[0];
        if (font.charWidths[cnt] > font.charWidthMax)
            font.charWidthMax = font.charWidths[cnt];
        cnt++;

        font.charHeight = font.fontHeight;

        //CALC CELL SIZES
        font.cellWidth = (int) font.charWidthMax + (2 * font.fontPadX);
        font.cellHeight = (int) font.charHeight + (2 * font.fontPadY);
        int maxSize = font.cellWidth > font.cellHeight ? font.cellWidth : font.cellHeight;
        if (maxSize < FONT_SIZE_MIN || maxSize > FONT_SIZE_MAX) {
            Log.e("Load Font Error", "Size error");
            return null;
        }

        //CALC TEXTURE SIZE
        if (maxSize <= 24)
            textureSize = 256;
        else if (maxSize <= 40)
            textureSize = 512;
        else if (maxSize <= 80)
            textureSize = 1024;
        else
            textureSize = 2048;

        //GENERATE BITMAP
        bitmap = Bitmap.createBitmap(textureSize, textureSize, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0x00000000);

        font.colCnt = textureSize / font.cellWidth;
        font.rowCnt = (int) Math.ceil((float) CHAR_CNT / (float) font.colCnt);

        //DRAW TEXTURE
        float x = font.fontPadX;
        float y = (font.cellHeight - 1) - font.fontDescent - font.fontPadY;
        for (char c = CHAR_START; c <= CHAR_END; c++) {
            s[0] = c;
            canvas.drawText(s, 0, 1, x, y, paint);
            x += font.cellWidth;
            if ((x + font.cellWidth - font.fontPadX) > textureSize) {
                x = font.fontPadX;
                y += font.cellHeight;
            }
        }

        s[0] = CHAR_NONE;
        canvas.drawText(s, 0, 1, x, y, paint);

        font.glTexture = loadGLTextureFromBitmap(bitmap);
        font.width = bitmap.getWidth();
        font.height = bitmap.getHeight();

        //TEST ONLY TODO
        font.load();
        //TEST ONLY
        font.loadFont();

        return font;
    }

    /*
     * PRIVATE METHODS
     */
    private static int loadGLTextureFromBitmap(Bitmap bitmap) {
        int textureId[] = new int[1];
        GLES20.glGenTextures(1, textureId, 0);
        //bind texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
        //FILTER TEXTURE
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_CLAMP_TO_EDGE);

        //NOTHING
        //Use Android to load Texture
        //GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        bitmap.recycle();
        if (textureId[0] == 0) {
            throw new RuntimeException("Error loading font.");
        }
        Log.i("GabEngine", "FontLoad! ID:" + textureId[0]);

        return textureId[0];
    }

    public static void init(Activity context) {
        FontFactory.context = context;
    }

    public void loadFontCharWidth() {

    }
}
