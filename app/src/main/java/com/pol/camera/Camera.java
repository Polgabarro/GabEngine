package com.pol.camera;

import android.opengl.Matrix;

import com.pol.entities.Entity;
import com.pol.entities.updateModifier.UpdateListener;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 18/12/14.
 */
public class Camera extends Entity {
    //MATRIX
    private final float[] mViewMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mVPMatrix = new float[16];
    private final float[] hudmVPMatrix = new float[16];
    //CAMERA
    CameraOrientation cameraOrientation;
    private int resolutionX, resolutionY;
    private UpdateListener followUpdateListener = null;

    /**
     * Create a new Camera
     *
     * @param posX              the position X of the camera
     * @param posY              the position Y of the camera
     * @param resolutionX       the resolution width of the camera
     * @param resolutionY       the resolution height of the camera
     * @param cameraOrientation the camera orientation
     */
    public Camera(float posX, float posY, int resolutionX, int resolutionY, CameraOrientation cameraOrientation) {
        super(posX, posY);
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;

        this.cameraOrientation = cameraOrientation;

        Matrix.setLookAtM(mViewMatrix, 0,
                0f, 0f, 1f,  // camera position
                0f, 0f, 0f,  //camera target
                0f, 1f, 0f);//up vector

        float ratio = (float) resolutionX / resolutionY;

        Matrix.orthoM(mProjectionMatrix, 0, -resolutionX / 2f + getX(), resolutionX / 2f + getX(), -resolutionY / 2f + getY(), resolutionY / 2f+getY(), -5, 50);
        Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        createHudmVPMatrix(resolutionX,resolutionY, cameraOrientation);

    }

    /**
     * Create a new Camera centred in 0,0
     *
     * @param resolutionX       the resolution width of the camera
     * @param resolutionY       the resolution height of the camera
     * @param cameraOrientation the camera orientation
     */
    public Camera(int resolutionX, int resolutionY, CameraOrientation cameraOrientation) {
        this(0, 0, resolutionX,resolutionY,cameraOrientation);
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

    public float[] getHudmVPMatrix(){
        return hudmVPMatrix;
    }
    /**
     * PUBLIC METHODS
     */
    /**
     * Set the position X
     *
     * @param x The position X
     */
    public void setX(float x) {
        super.setX(x);
        updateViewMatrix();
    }

    /**
     * Set the position Y
     *
     * @param y The position X
     */
    public void setY(float y) {
        super.setY(y);
        updateViewMatrix();
    }

    /**
     * Set's the position of a entity
     *
     * @param x The position X
     * @param y The position y
     */
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateViewMatrix();
    }

    /**
     * Follow an entity
     * @param entity
     */
    public void followEntity(final Entity entity) {
        if (followUpdateListener != null)
            removeUpdateListener(followUpdateListener);

        followUpdateListener = new UpdateListener() {
            @Override
            public void update(float elapsedTime) {
                setPosition(entity.getX(), entity.getY());
            }
        };
        addUpdateListener(followUpdateListener);
    }

    /**
     * Not follow any entity
     */
    public void unFollowEntity() {
        if (followUpdateListener != null) {
            removeUpdateListener(followUpdateListener);
            followUpdateListener = null;
        }
    }

    public CameraOrientation getCameraOrientation() {
        return cameraOrientation;
    }

    public void onSurfaceChanged(int width, int height) {
        // TODO Surface changed automatic
    }

    @Override
    public void render(float[] mVPMatrix) {

    }

    /**
     * SetZoom
     *
     * @param zoom
     */
    public void setZoom(float zoom) {
        setScale(zoom);
        updateViewMatrix();
    }


    /**
     * PRIVATE METHODS
     */
    private void updateViewMatrix() {

        if (mProjectionMatrix == null) {
            return;
        }
        Matrix.orthoM(mProjectionMatrix, 0, (-resolutionX / 2f) / getScaleX() + getX(), (resolutionX / 2f) / getScaleX() + getX(), (-resolutionY / 2f) / getScaleY() + getY(), (resolutionY / 2f) / getScaleY() + getY(), -5, 50);
        Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    private void createHudmVPMatrix(int resolutionX, int resolutionY, CameraOrientation cameraOrientation) {
        float[] hudmProjectionMatrix = new float[16];
        Matrix.orthoM(hudmProjectionMatrix, 0, -resolutionX / 2f, resolutionX / 2f, -resolutionY / 2f, resolutionY / 2f, -5, 50);
        Matrix.multiplyMM(hudmVPMatrix, 0, hudmProjectionMatrix, 0, mViewMatrix, 0);
    }
    //TODO ZOOM

}
