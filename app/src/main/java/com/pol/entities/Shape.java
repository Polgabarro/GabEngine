package com.pol.entities;

import android.opengl.GLES20;

import com.pol.utils.CntsOpenGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class Shape extends Entity {

    /**
     * VARIABLES
     */
    private boolean containFigure;
    protected float squareCords[];
    protected short squareIndex[];
    protected float color[] = {1f, 1f, 1f, 1f};

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    //OpenGL Render
    private int mProgram;
    //Vertex Shader Uniforms handlers
    private int mPositionHandle;
    private int mVPMatrixHandle;
    private int mModelMatrixHandle;

    //Fragment Shader Uniform handlers
    private int mColorHandle;


    /**
     * CONSTRUCTORS
     */
    public Shape(float x, float y) {
        super(x, y);
    }

    /**
     * PUBLIC METHODS
     */
    /**
     * Change the entity color
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
     * PROTECTED METHODS
     */
    protected void setShader(int idShader) {
        this.mProgram = idShader;
        //Set Uniforms
        mVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uVPMatrix");
        mModelMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uModelMatrix");
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");


    }

    protected void initVertex() {

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCords);
        vertexBuffer.position(0);
    }

    protected void initIndex() {
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                squareIndex.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(squareIndex);
        drawListBuffer.position(0);
    }

    @Override
    public void render(float[] mVPMatrix) {
        //super.render(mVPMatrix);

        GLES20.glUseProgram(mProgram);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(
                mPositionHandle, CntsOpenGL.COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CntsOpenGL.COORDS_PER_VERTEX * 4, vertexBuffer);


        GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mVPMatrix, 0);


        GLES20.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);


        GLES20.glUniform4fv(mColorHandle, 1, color, 0);


        // Draw the square
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, squareIndex.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);

        int length = entities.size();
        for (int i = 0; i < length; i++) {
            entities.get(i).render(mVPMatrix);
        }

    }


/**
 * PRIVATE METHODS
 */

}
