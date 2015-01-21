package com.pol.entities;

import android.opengl.Matrix;
import android.util.Log;

import com.pol.actions.Action;
import com.pol.entities.updateModifier.UpdateListener;

import java.util.ArrayList;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/12/14.
 */
public class Entity {

    /**
     * VARIABLES
     */
    private static int totalEntityCreated = 0;
    protected ArrayList<Entity> entities;
    //MODEL MATRIX'S
    protected float[] mModelMatrix = new float[16];
    private int id;
    private float x = 0, y = 0, z = 0;
    private float rotation = 0;
    private float scale = 1;
    private Entity parent = null;
    private float[] mScaleMatrix = new float[16];
    private float[] mTranslationMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];


    private boolean modelChanged = false;

    //ACTIONS
    private Action action = null;
    private UpdateListener updateListener = null;


    /**
     * CONSTRUCTORS
     */
    protected Entity() {
        id = totalEntityCreated;
        totalEntityCreated++;
        entities = new ArrayList<Entity>();
        Matrix.setIdentityM(mTranslationMatrix, 0);
        Matrix.setIdentityM(mScaleMatrix, 0);
        Matrix.setIdentityM(mRotationMatrix, 0);
    }

    protected Entity(float x, float y) {
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
        setTranslation(x - this.x, 0, 0);
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
        setTranslation(0, y - this.y, 0);
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
        setTranslation(x - this.x, y - this.y, 0);
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
     * @param entities Array of entities
     */

    public void attachChild(Entity[] entities) {
        for (Entity i : entities) {
            i.parent = this;
            this.entities.add(i);
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

        if (action != null)
            action.update(elapsedTime);

        if (updateListener != null)
            updateListener.update(elapsedTime);

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
     * Add an Action to Entity
     *
     * @param action the action
     */
    public void addAction(Action action) {
        this.action = action;
        this.action.setEntity(this);
    }

    /**
     * Remove the Action of Entity
     */
    public void removeAction() {
        if (action != null) {
        this.action.removeEntity();
        this.action = null;
        }
    }

    /**
     * Return action from entity
     *
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * Add an Action to Entity
     *
     * @param updateListener the action
     */
    public void addUpdateListener(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    /**
     * Remove the Action of Entity
     */
    public void removeUpdateListener() {
        this.updateListener = null;
    }

    /**
     * Return action from entity
     *
     * @return the action
     */
    public UpdateListener updateListener() {
        return updateListener;
    }

    /**
     * @return the Z Index
     */
    public float getZIndex() {
        return z;
    }

    /**
     * Set the Z Index
     *
     * @param zIndex
     */
    public void setZIndex(float zIndex) {
        modelChanged = true;
        setTranslation(0, 0, zIndex - this.z);

        this.z = zIndex;
    }

    /**
     * Set this entity behind this @param entity
     */
    public void setBehind(Entity entity) {
        setZIndex(entity.getZIndex() - 0.1f);
    }

    /**
     * Set this entity front this @param entity
     */
    public void setFront(Entity entity) {
        setZIndex(entity.getZIndex() + 0.1f);
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

    private void setTranslation(float x, float y, float z) {
        Matrix.translateM(mTranslationMatrix, 0, x, y, z);
    }

}
