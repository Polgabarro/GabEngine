package com.pol.entities.background;

import android.opengl.GLES20;

import com.pol.entities.Sprite;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/12/14.
 */
public class Background {
    private float color[] = new float[]{0, 0, 0};
    private Sprite sprite;
    private boolean colorBackground = false;
    private boolean spriteBackground = false;

    /*
     * CONSTRUCTORS
     */

    /**
     * Create a background with a color
     *
     * @param color float RGB
     */
    public Background(float[] color) {
        this.color = color;
        colorBackground = true;
    }

    /**
     * Create a background with a color
     *
     * @param R red color
     * @param G green color
     * @param B blue color
     */
    public Background(float R, float G, float B) {
        this.color[0] = R;
        this.color[1] = G;
        this.color[2] = B;
        colorBackground = true;
    }

    /**
     * Create a background with a sprite
     *
     * @param sprite
     */
    public Background(Sprite sprite) {
        this.sprite = sprite;
        spriteBackground = true;
    }

    /**
     * @return the color of the background
     */
    public float[] getColor() {
        return color;
    }

    /**
     * Set a different color to the background
     *
     * @param color
     */
    public void setColor(float[] color) {
        this.color = color;
        colorBackground = true;
        spriteBackground = false;
        sprite = null;
    }

    /**
     * Set a different sprite to the background
     *
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        colorBackground = false;
        spriteBackground = true;
    }

    public boolean isColorBackground() {
        return colorBackground;
    }

    public boolean isSpriteBackground() {
        return spriteBackground;
    }

    public void render(float[] mVPMatrix) {
        if (colorBackground) {
            GLES20.glClearColor(color[0], color[1], color[2], 1);
        } else if (spriteBackground) {
            sprite.render(mVPMatrix);
        }
    }

}
