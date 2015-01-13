package com.pol.entities;

import android.opengl.GLES20;
import android.util.Log;

import com.pol.graphics.Shader;
import com.pol.utils.CntsOpenGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 07/01/15.
 */

public class ShapeBatches extends Entity {

    private static Map<Integer, Shape> shapeMap;
    protected float shapeCords[];
    protected short shapeIndex[];
    protected float color[] = {1f, 1f, 1f, 1f};
    /**
     * VARIABLES
     */
    private boolean containFigure;
    private int totalEntitys = 0;
    private int maxShapes;

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    //OpenGL Render
    private int mProgram;
    //Vertex Shader Uniforms handlers
    private int mPositionHandle;
    private int mVPMatrixHandle;
    private int mModelMatrixHandle0;
    private int mModelMatrixHandle1;
    private int mModelMatrixHandle2;
    private int mModelMatrixHandle3;

    //Fragment Shader Uniform handlers
    private int mColorHandle;

    /**
     * CONSTRUCTORS
     */
    public ShapeBatches(float x, float y, int nShapes) {
        super(x, y);
        setShader(Shader.LoadShaders("shader/ShapeBatches_VS.glsl", "shader/ShapeBatches_FS.glsl"));
        //TODO Optimitze the number of vertex
        shapeCords = new float[CntsOpenGL.CORDS_PER_VERTEX * nShapes * 4];
        shapeIndex = new short[6 * nShapes];
        shapeMap = new HashMap<Integer, Shape>();
        this.maxShapes = nShapes;
    }

    /**
     * PUBLIC METHODS
     */
    public int addShape(Shape shape) {
        if (totalEntitys >= maxShapes) {
            //RESIZE THINGS
            Log.d("GabEngine Perfomance", "Rezing ShapeBatches");
        }
        System.arraycopy(shape.shapeCords, 0, shapeCords, 4 * totalEntitys * CntsOpenGL.CORDS_PER_VERTEX, shape.shapeCords.length);
        System.arraycopy(shape.shapeIndex, 0, shapeIndex, 6 * totalEntitys, shape.shapeIndex.length);

        shapeMap.put(totalEntitys, shape);
        int id = 0;
        return id;
    }


    /**
     * Change all shapes color
     *
     * @param R is the red component of the color
     * @param G is the green component of the color
     * @param B is the blue component of the color
     */
    public void setColor(float R, float G, float B) {
        color[0] = R;
        color[1] = G;
        color[2] = B;
    }

    /**
     * Change the color of the shape with the same id
     *
     * @param R  is the red component of the color
     * @param G  is the green component of the color
     * @param B  is the blue component of the color
     * @param id is the id of the shape that you want to change
     */
    public void setColor(float R, float G, float B, int id) {
        color[0] = R;
        color[1] = G;
        color[2] = B;
    }

    /**
     * Get the position of the shape with the same id
     *
     * @param id is the identification of the shape
     * @return the position X
     */
    public float getX(int id) {
        return super.getX();
    }

    /**
     * Set the position X of the shape with the same id
     *
     * @param id is the identification of the shape
     * @param x  The position X
     */
    public void setX(float x, int id) {
        super.setX(x);
    }

    /**
     * Get the position Y of the shape with the same id
     *
     * @param id is the identification of the shape
     * @return
     */
    public float getY(int id) {
        return super.getY();
    }

    /**
     * Set the position Y of the shape with the same id
     *
     * @param id is the identification of the shape
     * @param y  The position X
     */
    public void setY(float y, int id) {
        super.setY(y);
    }

    /**
     * Set's the position of a entity
     *
     * @param id is the identification of the shape
     * @param x  The position X
     * @param y  The position y
     */
    public void setPosition(float x, float y, int id) {
        super.setPosition(x, y);
    }

    /**
     * PROTECTED METHODS
     */
    protected void setShader(int idShader) {
        this.mProgram = idShader;
        //Set Uniforms
        mVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uVPMatrix");

        mModelMatrixHandle0 = GLES20.glGetAttribLocation(mProgram, "uModelMatrix0");
        mModelMatrixHandle1 = GLES20.glGetAttribLocation(mProgram, "uModelMatrix1");
        mModelMatrixHandle2 = GLES20.glGetAttribLocation(mProgram, "uModelMatrix2");
        mModelMatrixHandle3 = GLES20.glGetAttribLocation(mProgram, "uModelMatrix3");

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

    }

    protected void initVertex() {

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                shapeCords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(shapeCords);
        vertexBuffer.position(0);
    }

    protected void initIndex() {
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                shapeIndex.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(shapeIndex);
        drawListBuffer.position(0);
    }

    @Override
    public void render(float[] mVPMatrix) {
        //super.render(mVPMatrix);

        GLES20.glUseProgram(mProgram);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(
                mPositionHandle, CntsOpenGL.CORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CntsOpenGL.CORDS_PER_VERTEX * 4, vertexBuffer);


        GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mVPMatrix, 0);


        GLES20.glUniform4fv(mColorHandle, 1, color, 0);


        // Draw the square
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, shapeIndex.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);

        int length = entities.size();
        for (int i = 0; i < length; i++) {
            entities.get(i).render(mVPMatrix);
        }

    }

}
