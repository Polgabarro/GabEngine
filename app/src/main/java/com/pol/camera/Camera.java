package com.pol.camera;

import android.opengl.Matrix;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 18/12/14.
 */
public class Camera {
    //MATRIX
    private final float[] mViewMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mVPMatrix = new float[16];
    //CAMERA
    CameraOrientation cameraOrientation;
    private int resolutionX, resolutionY;


    public Camera(int resolutionX, int resolutionY, CameraOrientation cameraOrientation) {

        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;

        this.cameraOrientation = cameraOrientation;

        Matrix.setLookAtM(mViewMatrix, 0,
                0f, 0f, 1f,  // camera position
                0f, 0f, 0f,  //camera target
                0f, 1f, 0f);//up vector

        float ratio = (float) resolutionX / resolutionY;

        Matrix.orthoM(mProjectionMatrix, 0, -resolutionX / 2f, resolutionX / 2f, -resolutionY / 2f, resolutionY / 2f, -5, 50);
        Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public float[] getmVPMatrix() {
        return mVPMatrix;
    }
    /**
     * PUBLIC METHODS
     */

    /**
     * PROTECTED METHODS
     */
    public CameraOrientation getCameraOrientation() {
        return cameraOrientation;
    }

    public void onSurfaceChanged(int width, int height) {
        // TODO Surface changed automatic
    }
}
