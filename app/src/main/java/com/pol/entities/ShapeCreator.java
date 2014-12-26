package com.pol.entities;

import com.pol.graphics.Shader;
import com.pol.utils.CntsOpenGL;
import com.pol.utils.GabMath;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class ShapeCreator {


    /**
     * Create a Rectangle
     *
     * @param posX   The position X
     * @param posY   The position Y
     * @param witdh  The width of shape
     * @param heigth The height of shape
     * @return the rectangle
     */
    public static Shape createRectangle(float posX, float posY, float witdh, float heigth) {
        Shape shape = new Shape(posX, posY);
        shape.shapeCords = new float[]{
                -witdh / 2, heigth / 2, 0, //top left
                -witdh / 2, -heigth / 2, 0,//bottom left
                witdh / 2, -heigth / 2, 0,//bottom right
                witdh / 2, heigth / 2, 0 // top right
        };
        shape.shapeIndex = new short[]{
                0, 1, 2, 2, 3, 0
        };
        return createShape(shape);
    }

    /**
     * Create a Rectangle
     *
     * @param posX   The position X
     * @param posY   The position Y
     * @param witdh  The width of shape
     * @param heigth The height of shape
     * @param color
     * @return the rectangle
     */
    public static Shape createRectangle(float posX, float posY, float witdh, float heigth, float color[]) {

        Shape shape = createRectangle(posX, posY, witdh, heigth);
        shape.setColor(color[0], color[1], color[2]);

        return shape;
    }

    @Deprecated
    public static Shape createSphere(float posX,float posY, float radius){
        Shape shape = new Shape(posX,posY);
        //shape.shapeCords = new float[5* CntsOpenGL.COORDS_PER_VERTEX];
        //shape.shapeIndex = new short[5];
        int precision=100;
        shape.shapeCords = new float[(precision+1)* CntsOpenGL.COORDS_PER_VERTEX];
        shape.shapeIndex = new short[precision*3];

        GabMath.createCircumferenceVectors(50, shape.shapeCords,shape.shapeIndex,radius);

        return createShape(shape);
    }

    /*
     * PRIVATE STATIC METHODS
     */
    private static Shape createShape(Shape shape) {
        shape.setShader(Shader.LoadShaders("shaders/Simple_VS.glsl", "shaders/Simple_FS.glsl"));
        shape.initVertex();
        shape.initIndex();
        return shape;
    }
}
