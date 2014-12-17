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
    protected float squareCoords[];
    protected short squareIndex[];
    protected float color[]={1,1,1,1};

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;


    private int mProgram;
    private int mPositionHandle;

    /**
     * CONSTRUCTORS
     */
    public Shape(float x, float y) {
        super(x, y);
    }

    /**
     * PUBLIC METHODS
     */
    public   void setColor(float RED, float GREEN, float BLUE) {

    }
    /**
     * PROTECTED METHODS
     */
    protected void setShader(int idShader){
        this.mProgram = idShader;
    }

    protected void initVertex() {

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
        squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);
    }
    protected void initIndex(){
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                squareIndex.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(squareIndex);
        drawListBuffer.position(0);
    }

    @Override
    public void render() {
        super.render();

        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(
                mPositionHandle, CntsOpenGL.COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CntsOpenGL.COORDS_PER_VERTEX * 4, vertexBuffer);

        // Draw the square

        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, squareIndex.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }


/**
     * PRIVATE METHODS
     */

}
