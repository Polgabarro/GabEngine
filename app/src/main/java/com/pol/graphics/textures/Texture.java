package com.pol.graphics.textures;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public class Texture {
    protected int glTexture;
    protected int width;
    protected int height;
    protected FloatBuffer uvBuffer;
    private float uvCords[] = new float[]{
            1, 0,  //bottom right
            1, 1,  //top right
            0, 1, //top left
            0, 0 //bottom left
    };
    private boolean subRegion = false;
    private boolean atlas = false;

    /*
     * PUBLIC METHODS
     */
    public FloatBuffer getUvBuffer() {
        return uvBuffer;
    }


    public int getGlTexture() {
        return glTexture;
    }

    /**
     * @return the texture width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the texture height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width    the subtexture height
     * @param height   the subtexture width
     * @param bottomLeftX the topLeft X component
     * @param bottomLeftY the topLeft Y component
     * @return the texture region
     */
    public Texture getTextureRegion(int width, int height, float bottomLeftX, float bottomLeftY) {
        Texture texture = new Texture();
        texture.glTexture = this.glTexture;
        texture.width = width;
        texture.height = height;
        calcUVCords(width, height, bottomLeftX, bottomLeftY);
        texture.load();
        texture.subRegion = true;
        atlas = true;
        return texture;
    }

    /*
     * PROTECTED METHODS
     */
    protected void load() {
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                uvCords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uvCords);
        uvBuffer.position(0);
    }

    /*
     * PRIVATE METHODS
     */
    private void calcUVCords(int width, int height, float bottomLeftX, float bottomLeftY) {
        uvCords[0] = (bottomLeftX + width) / this.width;
        uvCords[1] = (bottomLeftY) / this.height;
        uvCords[2] = uvCords[0];
        uvCords[3] = (bottomLeftY + height) / this.height;
        uvCords[4] = (bottomLeftX) / this.width;
        uvCords[5] = uvCords[3];
        uvCords[6] = uvCords[4];
        uvCords[7] = uvCords[1];
    }
}
