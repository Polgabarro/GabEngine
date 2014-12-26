package com.pol.entities.background;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/12/14.
 */
public class Background {
    private float color[] = new float[]{0, 0, 0};
    private boolean colorBackground = false;

    public Background(float[] color) {
        this.color = color;
        colorBackground = true;
    }

    public Background(float R, float G, float B) {
        this.color[0] = R;
        this.color[0] = G;
        this.color[0] = B;
        colorBackground = true;
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float[] color) {
        this.color = color;
        colorBackground = true;
    }

    public boolean isColorBackground() {
        return colorBackground;
    }

}
