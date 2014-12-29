package com.pol.entities;

import android.util.Log;

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
     * @param color  The color of rectangle
     * @return the rectangle
     */
    public static Shape createRectangle(float posX, float posY, float witdh, float heigth, float color[]) {

        Shape shape = createRectangle(posX, posY, witdh, heigth);
        shape.setColor(color[0], color[1], color[2]);

        return shape;
    }

    /**
     * Create a Polygon
     *
     * @param posX The position X
     * @param posY The position Y
     * @param nVertex The number of vertex (or sides) of the polygon
     * @param radius The radius
     * @return a polygon
     */
    public static Shape createPolygon(float posX, float posY, int nVertex, float radius){
        Shape shape = new Shape(posX,posY);

        shape.shapeCords = new float[(nVertex+1)* CntsOpenGL.COORDS_PER_VERTEX];
        shape.shapeIndex = new short[(nVertex)*3];

        GabMath.createCircumferenceVectors(nVertex, shape.shapeCords, shape.shapeIndex, radius);

        return createShape(shape);

    }

    /**
     * Create a Polygon
     *
     * @param posX The position X
     * @param posY The position Y
     * @param nVertex The number of vertex (or sides) of the polygon
     * @param radius The radius
     * @param color The color of the polygon
     *
     * @return a polygon
     */
    public static Shape createPolygon(float posX, float posY, int nVertex, float radius,float color[] ){
        Shape shape = createPolygon( posX,  posY,  nVertex,  radius);
        shape.setColor(color[0], color[1], color[2]);
        return shape;
    }

    /**
     * Create a Circle
     *
     * @param posX The position X
     * @param posY The position Y
     * @param radius The radius
     * @return a circle
     */
    public static Shape createCircle(float posX, float posY, float radius){

        int num_vertices=(int)(radius/4f);
        //double th= (int) Math.acos(2d * (1d - 0.1d / radius)*(1d - 0.5d / radius) - 1d);
        //int num_vertices = (int) Math.ceil(2 * Math.PI / th);
        Log.i("NUmber","V:"+num_vertices);
        return createShape(createPolygon(posX,posY,num_vertices,radius));
    }

    /**
     *
     * @param posX The position X
     * @param posY The position Y
     * @param radius The radius
     * @param color The color of the circle
     * @return a circle
     */
    public static Shape createCircle(float posX, float posY, float radius, float color[]){
        Shape shape = createCircle(posX, posY, radius);
        shape.setColor(color[0], color[1], color[2]);
        return shape;
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
