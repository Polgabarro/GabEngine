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
    private float uvCords[];

    /*
     * PUBLIC METHODS
     */

    public FloatBuffer getUvBuffer() {
        return uvBuffer;
    }

    public int getGlTexture() {
        return glTexture;
    }

    /*
             * PROTECTED METHODS
             */
    protected void load() {

        uvCords = new float[]{
                1, 0,  //bottom right
                1, 1,  //top right
                0, 1, //top left
                0, 0 //bottom left
        };
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                uvCords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uvCords);
        uvBuffer.position(0);
    }

    protected void load(float topLeft, float bottomLeft, float bottomRight, float topRight) {

    }

}
