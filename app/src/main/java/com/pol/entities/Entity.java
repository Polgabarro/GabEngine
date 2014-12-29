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
    private static int totalEntityCreated = 0;
    private int id;


    private float x = 0, y = 0;
    protected ArrayList<Entity> entities;
    private Entity parent = null;

    //MODEL MATRIX'S
    protected float[] mModelMatrix = new float[16];
    private float[] mScaleMatrix = new float[16];
    private float[] mTranslationMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];

    private boolean modelChanged = false;


    /**
     * CONSTRUCTORS
     */
    public Entity() {
        id = totalEntityCreated;
        totalEntityCreated++;
        entities = new ArrayList<Entity>();
        Matrix.setIdentityM(mTranslationMatrix, 0);
        Matrix.setIdentityM(mScaleMatrix, 0);
        Matrix.setIdentityM(mRotationMatrix, 0);
    }

    public Entity(float x, float y) {
        this();
        setPosition(x, y);
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

        modelChanged = true;
        setTranslation(x - this.x, 0);
        this.x = x;
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

        modelChanged = true;
        setTranslation(0, y - this.y);
        this.y = y;
    }

    /**
     * Set's the position of a entity
     *
     * @param x The position X
     * @param y The position y
     */
    public void setPosition(float x, float y) {
        modelChanged = true;
        setTranslation(x - this.x, y - this.y);
        this.x = x;
        this.y = y;
    }

    /**
     * Attach a child
     *
     * @param entity The entity
     */
    public void attachChild(Entity entity) {
        if (entity == this) {
            Log.e("GabEngine Error", "The children cannot be the parent");
            System.exit(0);
        }
        entity.parent = this;
        entities.add(entity);

    }

    /**
     * Attach a child
     *
     * @param entites Array of entities
     */

    public void attachChild(Entity[] entites) {
    for(Entity i:entites) {
        i.parent = this;
        entities.add(i);
    }
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
        entity.parent = null;
        entity.removeChild(entity);
    }


    public void update(float elapsedTime) {

        makeModelTransformations();

        int length = entities.size();

        for (int i = 0; i < length; i++) {
            entities.get(i).update(elapsedTime);
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

        if (modelChanged || parentChanged()) {
            //Log.i("ENTER", "makeMODELENTRING ID:" + id);
            float[] temp = new float[16];
            Matrix.multiplyMM(temp, 0, mRotationMatrix, 0, mScaleMatrix, 0);
            Matrix.multiplyMM(mModelMatrix, 0, mTranslationMatrix, 0, temp, 0);

            if (parent != null)
                Matrix.multiplyMM(mModelMatrix, 0, mModelMatrix, 0, parent.mModelMatrix, 0);

        }
    }

    private Boolean parentChanged() {
        if (parent != null) {
            return parent.modelChanged;
        }
        return false;
    }

    private void setTranslation(float x, float y) {
        Matrix.translateM(mTranslationMatrix, 0, x, y, 0);
    }


}
