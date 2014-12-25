package com.pol.entities;

import android.opengl.Matrix;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/12/14.
 */
public class Entity {

    /**
     * VARIABLES
     */
    private float x, y;
    protected ArrayList<Entity> entities;
    private Entity parent = null;

    //MODEL MATRIX'S
    protected float[] mModelMatrix = new float[16];

    private float[] mScaleMatrix = new float[16];
    private boolean scaleChanged = false;
    private float[] mTranslationMatrix = new float[16];
    private boolean translationChanged = false;
    private float[] mRotationMatrix = new float[16];
    private boolean rotationChanged = false;

    private boolean modelChanged = false;


    /**
     * CONSTRUCTORS
     */
    public Entity() {
        entities = new ArrayList<Entity>();
    }

    public Entity(float x, float y) {
        this();
        this.x = x;
        this.y = y;

        Matrix.setIdentityM(mTranslationMatrix, 0);
        setTranslation();

        mModelMatrix = mTranslationMatrix;


    }

    /**
     * PUBLIC METHODS
     */

    /**
     * Get the position
     *
     * @return the position X
     */
    public float getX() {
        return x;
    }

    /**
     * Set the position X
     *
     * @param x The position X
     */
    public void setX(float x) {
        this.x = x;
        translationChanged = true;
    }

    /**
     * Get the position Y
     *
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Set the position Y
     *
     * @param y The position X
     */
    public void setY(float y) {
        this.y = y;
        translationChanged = true;
    }

    /**
     * Set's the position of a entity
     *
     * @param x The position X
     * @param y The position y
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        translationChanged = true;
    }

    /**
     * Attach a child
     *
     * @param entity
     */
    public void attachChild(Entity entity) {
        if (entity == this) {
            Log.e("GabEngine Error", "The children cannot be the parent");
            System.exit(0);
        }
        entity.parent = this;
        entities.add(entity);

    }

    public boolean hasParent() {
        return parent != null ? true : false;
    }

    /**
     * Remove the child
     *
     * @param entity
     */
    public void removeChild(Entity entity) {
        entity.removeChild(entity);
    }


    protected void update() {

        makeModelTransformations();

        int length = entities.size();

        for (int i = 0; i < length; i++) {
            entities.get(i).update();
        }
        modelChanged = false;
    }


    public void render(float[] mVPMatrix) {
        int length = entities.size();
        for (int i = 0; i < length; i++) {
            entities.get(i).render(mVPMatrix);
        }

    }

    /**
     * PRIVATE METHODS
     */
    private void makeModelTransformations() {

        if (translationChanged) {
            setTranslation();
        }
        if (scaleChanged) {
            //TODO scale
        }
        if (rotationChanged) {
            //TODO rotation
        }

        //Matrix.multiplyMM(mModelMatrix,0,);
        if (translationChanged || scaleChanged || rotationChanged) {
            mModelMatrix = mTranslationMatrix;
            modelChanged = true;
        }
        /*if(parent!=null){
                Matrix.multiplyMM(mModelMatrix,0,parent.mModelMatrix,0,mModelMatrix,0);
        }*/


    }

    private void setTranslation() {
        Matrix.translateM(mTranslationMatrix, 0, x, y, 0);
    }


}
