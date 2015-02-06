package com.pol.graphics.textures;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public class Texture {
    public float uvCords[] = new float[]{
            0, 0, //bottom left
            0, 1, //top left
            1, 1,  //top right
            1, 0  //bottom right
    };
    protected int glTexture;
    protected int width;
    protected int height;
    protected FloatBuffer uvBuffer;
    protected boolean subRegion = false;
    protected boolean atlas = false;

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
     * @param width    the subTexture height
     * @param height   the subTexture width
     * @param topLeftX the topLeft X component
     * @param topLeftY the topLeft Y component
     * @return the texture region
     */
    public Texture getTextureRegion(int width, int height, float topLeftX, float topLeftY) {
        Texture texture = new Texture();
        texture.glTexture = this.glTexture;
        texture.width = width;
        texture.height = height;
        calcUVCords(width, height, topLeftX, topLeftY, texture);
        texture.load();
        texture.subRegion = true;
        atlas = true;
        return texture;
    }

    /**
     * Delete Texture from memory
     */
    public void deleteTexture() {
        int[] texture = {glTexture};
        TextureFactory.deleteTexture(texture);
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


    protected void calcUVCords(float width, float height, float bottomLeftX, float bottomLeftY, Texture texture) {
        texture.uvCords[0] = (bottomLeftX) / this.width;
        texture.uvCords[1] = 1 - (this.height - bottomLeftY) / this.height;

        texture.uvCords[2] = texture.uvCords[0];
        texture.uvCords[3] = 1 - (this.height - bottomLeftY - height) / this.height;

        texture.uvCords[4] = (bottomLeftX + width) / this.width;
        texture.uvCords[5] = texture.uvCords[3];

        texture.uvCords[6] = texture.uvCords[4];
        texture.uvCords[7] = texture.uvCords[1];
    }
        /*
     * PRIVATE METHODS
     */
}
