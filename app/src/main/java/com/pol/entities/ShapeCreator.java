package com.pol.entities;

import com.pol.graphics.Shader;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class ShapeCreator {


    /**
     * Create a Rectangle
     *
     * @param posX The position X
     * @param posY The position Y
     * @param witdh The width of shape
     * @param heigth The height of shape
     * @return the rectangle
     */
    public static Shape createRectangle(float posX, float posY, float witdh, float heigth) {
        Shape shape = new Shape(posX, posY);
        shape.squareCoords = new float[]{
                -witdh / 2, heigth / 2, 0, //top left
                -witdh / 2, -heigth / 2, 0,//bottom left
                witdh / 2, -heigth / 2, 0,//bottom right
                witdh / 2, heigth / 2, 0 // top right
        };
        shape.squareIndex = new short[]{
                0,1,2,2,3,0
        };

        shape.setShader(Shader.LoadShaders("Simple_VS.glsl", "Simple_FS.glsl"));

        shape.initVertex();
        shape.initIndex();

        return shape;
    }

    /**
     * Create a Rectangle
     *
     * @param posX The position X
     * @param posY The position Y
     * @param witdh The width of shape
     * @param heigth The height of shape
     * @param color
     * @return the rectangle
     */
    public static Shape createRectangle(float posX, float posY, float witdh, float heigth, float color[]) {

        Shape shape = createRectangle(posX,posY,witdh,heigth);
        shape.setColor(color[0],color[1],color[2]);

        return  shape;
    }
}
