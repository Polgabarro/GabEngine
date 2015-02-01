package com.pol.graphics.textures;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 29/01/15.
 */
public class Font extends Texture {

    public final float[] charWidths = new float[FontFactory.CHAR_CNT];
    public float fontHeight;
    public float fontAscent;
    public float fontDescent;
    public int textureId;
    public float charWidthMax;
    public float charHeight;
    public int cellWidth, cellHeight;
    public int rowCnt, colCnt;
    public float scaleX, scaleY;
    public float spaceX;
    protected int size;
    protected int fontPadX, fontPadY;
    private Texture[] charRgn;

    /*
     * PUBLIC METHODS
     */

    /**
     * Return a TextureRegion of a letter
     *
     * @param letter the letter
     * @return the texture region
     */
    public Texture getLetter(char letter) {
        return charRgn[letter - FontFactory.CHAR_START];
    }

    /*
     * PROTECTED METHODS
     */
    protected void loadFont() {
        float x = 0;
        float y = 0;
        charRgn = new Texture[FontFactory.CHAR_CNT];

        for (int c = 0; c < FontFactory.CHAR_CNT; c++) {
            charRgn[c] = getFontRegion(cellWidth - 1, cellHeight - 1, x, y);
            x += cellWidth;
            if (x + cellWidth > width) {
                x = 0;
                y += cellHeight;
            }
        }
    }

    protected Texture getFontRegion(int width, int height, float topLeftX, float topLeftY) {
        Texture texture = new Texture();
        texture.glTexture = this.glTexture;
        texture.width = width;
        texture.height = height;
        calcUVCords(width, height, topLeftX, topLeftY, texture);
        texture.subRegion = true;
        atlas = true;
        return texture;
    }
}
