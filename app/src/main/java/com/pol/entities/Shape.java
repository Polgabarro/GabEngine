package com.pol.entities;

import android.opengl.GLES20;

import com.pol.managers.TouchManager;
import com.pol.utils.CnsOpenGL;
import com.pol.utils.io.OnTouchListener;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class Shape extends Entity {

    protected float shapeCords[];
    protected short shapeIndex[];
    protected float color[] = {1f, 1f, 1f, 1f};
    protected FloatBuffer vertexBuffer;
    protected ShortBuffer drawListBuffer;
    //OpenGL Render
    protected int mProgram;
    //Vertex Shader Uniforms handlers
    protected int mPositionHandle;
    protected int mVPMatrixHandle;
    protected int mModelMatrixHandle;
    //Fragment Shader Uniform handlers
    protected int mColorHandle;
    /**
     * VARIABLES
     */
    private float width, height;
    private boolean containFigure;
    private OnTouchListener onTouchListener = null;


    /**
     * CONSTRUCTORS
     */
    protected Shape(float x, float y) {
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
        this.color[0] = R;
        this.color[1] = G;
        this.color[2] = B;
    }

    /**
     *
     * @return the color
     */
    public float[] getColor() {
        return this.color;
    }

    /**
     * @return the alpha
     */
    public float getAlpha() {
        return this.color[3];
    }


    /**
     * Set the transparency of a shape
     *
     * @param alpha
     */
    public void setAlpha(float alpha) {
        this.color[3] = alpha;
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
                mPositionHandle, CnsOpenGL.CORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                CnsOpenGL.CORDS_PER_VERTEX * 4, vertexBuffer);


        GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mVPMatrix, 0);


        GLES20.glUniformMatrix4fv(mModelMatrixHandle, 1, false, mModelMatrix, 0);


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

    /**
     * Set Touch Listener to a shape
     * @param onTouchListener
     */
    public void attachOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
        TouchManager.getInstance().addTouchDetection(this);
    }

    /**
     * Remove the touch Listener
     */
    public void removeOnTouchListener() {
        this.onTouchListener = null;
        TouchManager.getInstance().removeTouchDetection(this);
    }

    public OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }

    /**
     * @return the shape width
     */
    public float getWidth() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the shape height
     */
    public float getHeight() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }
    /**
     * PRIVATE METHODS
 */

}
