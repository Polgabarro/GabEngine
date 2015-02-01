package com.pol.entities;


import android.opengl.GLES20;

import com.pol.graphics.textures.Font;
import com.pol.graphics.textures.FontFactory;
import com.pol.graphics.textures.Texture;
import com.pol.utils.CnsOpenGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/01/15.
 */
public class Text extends Shape {
    protected Font font;
    protected FloatBuffer uvBuffer;
    private String sText = "";
    private int mTexture;
    private int mUV;
    private float uvCords[];
    private int textLength;

    /**
     * CONSTRUCTORS
     *
     * @param x
     * @param y
     */
    protected Text(float x, float y) {
        super(x, y);
    }

    /**
     * Set a new String
     *
     * @param sText the text String
     */
    public void setText(String sText) {
        if (this.sText.equals(sText)) {
            return;
        }
        this.sText = sText;

        createVertex();
        createIndex();
        createUV();

        initVertex();
        initIndex();
        initUV();
        setWidth(textLength);
        setHeight(font.cellHeight);
    }

    @Override
    public void render(float[] mVPMatrix) {
        if (sText.length() > 0) {
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
                    CnsOpenGL.UV_PER_VERTEX * 4, uvBuffer);


            GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mVPMatrix, 0);


            GLES20.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);


            GLES20.glUniform4fv(mColorHandle, 1, color, 0);

            //ACTIVE TEXTURE
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, font.getGlTexture());
            GLES20.glUniform1i(mTexture, 0);

            // Draw the square
            GLES20.glDrawElements(
                    GLES20.GL_TRIANGLES, shapeIndex.length,
                    GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

            GLES20.glDisableVertexAttribArray(mPositionHandle);
            GLES20.glDisableVertexAttribArray(mUV);
        }
        int length = entities.size();
        for (int i = 0; i < length; i++) {
            entities.get(i).render(mVPMatrix);
        }
        if (scaleX < 0 || scaleY < 0)
            GLES20.glEnable(GLES20.GL_CULL_FACE);
    }

    /*
     * PRIVATE METHODS
     */
    private void createVertex() {
        int length = sText.length();
        shapeCords = new float[length * 4 * 3];
        float x = 0;
        float y = font.cellHeight / 2;

        for (int i = 0; i < length; i++) {
            shapeCords[i * 12] = x;
            shapeCords[i * 12 + 1] = y;
            shapeCords[i * 12 + 2] = 0;
            shapeCords[i * 12 + 3] = x;
            shapeCords[i * 12 + 4] = -y;
            shapeCords[i * 12 + 5] = 0;
            shapeCords[i * 12 + 6] = x + font.cellWidth;
            shapeCords[i * 12 + 7] = -y;
            shapeCords[i * 12 + 8] = 0;
            shapeCords[i * 12 + 9] = x + font.cellWidth;
            shapeCords[i * 12 + 10] = y;
            shapeCords[i * 12 + 11] = 0;

            x += font.charWidths[sText.charAt(i) - FontFactory.CHAR_START];
        }
        textLength = (int) x;

    }

    private void createIndex() {
        int length = sText.length();
        shapeIndex = new short[6 * length];
        for (int i = 0; i < length; i++) {
            shapeIndex[i * 6] = (short) ((short) 4 * i);
            shapeIndex[i * 6 + 1] = (short) ((short) 4 * i + 1);
            shapeIndex[i * 6 + 2] = (short) ((short) 4 * i + 2);
            shapeIndex[i * 6 + 3] = (short) ((short) 4 * i + 2);
            shapeIndex[i * 6 + 4] = (short) ((short) 4 * i + 3);
            shapeIndex[i * 6 + 5] = (short) ((short) 4 * i);
        }

    }

    private void createUV() {
        int length = sText.length();
        uvCords = new float[8 * length];
        for (int i = 0; i < length; i++) {
            Texture texture = font.getLetter(sText.charAt(i));
            uvCords[i * 8] = texture.uvCords[0];
            uvCords[i * 8 + 1] = texture.uvCords[1];
            uvCords[i * 8 + 2] = texture.uvCords[2];
            uvCords[i * 8 + 3] = texture.uvCords[3];
            uvCords[i * 8 + 4] = texture.uvCords[4];
            uvCords[i * 8 + 5] = texture.uvCords[5];
            uvCords[i * 8 + 6] = texture.uvCords[6];
            uvCords[i * 8 + 7] = texture.uvCords[7];
        }
    }

    private void initUV() {
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                uvCords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uvCords);
        uvBuffer.position(0);
    }


}
