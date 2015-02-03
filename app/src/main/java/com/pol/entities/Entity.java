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
    protected float scaleX = 1;
    protected float scaleY = 1;
    protected boolean inHud = false;
    protected boolean inScene = false;
    protected boolean visible = true;
    private int id;
    private float x = 0, y = 0, z = 0;
    private float rotation = 0;
    private Entity parent = null;
    private float[] mScaleMatrix = new float[16];
    private float[] mTranslationMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];
    private boolean modelChanged = false;
    //ACTIONS
    private Action action = null;
    private ArrayList<UpdateListener> updateListeners = new ArrayList<UpdateListener>();


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
    public void detachChild(Entity entity) {
        entity.parent = null;
        entity.detachChild(entity);
    }


    public void update(float elapsedTime) {

        if (action != null)
            action.update(elapsedTime);

        if (updateListeners.size() != 0) {
            for (UpdateListener updateListener : updateListeners)
                updateListener.update(elapsedTime);
        }

        makeModelTransformations();

        int length = entities.size();

        for (int i = 0; i < length; i++) {
            entities.get(i).update(elapsedTime);
        }
        modelChanged = false;
    }


    public void render(float[] mVPMatrix) {
        if (!visible) return;

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
        if (this.action != null) {
            removeAction();
        }
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
        updateListeners.add(updateListener);
    }

    /**
     * Remove the Action of Entity
     */
    public void removeAllUpdateListeners() {
        updateListeners.clear();
    }

    /**
     * Remove an update Listener
     *
     * @param updateListener
     * @return true if the remove is successful
     */
    public boolean removeUpdateListener(UpdateListener updateListener) {
        return updateListeners.remove(updateListener);
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
     * Scale the entity
     *
     * @param scaleX component X of the scale
     * @param scaleY component Y of the scale
     */
    public void setScale(float scaleX, float scaleY) {
        modelChanged = true;
        setScaling(scaleX, scaleY);
        this.scaleX = scaleX;
        this.scaleY = scaleY;

    }

    public float getScaleX() {
        return scaleX;
    }

    /**
     * Scale the entity
     *
     * @param scaleX component X of the scale
     */
    public void setScaleX(float scaleX) {
        modelChanged = true;
        setScaling(scaleX, 1);
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    /**
     * Scale the entity
     *
     * @param scaleY component Y of the scale
     */
    public void setScaleY(float scaleY) {
        modelChanged = true;
        setScaling(1, scaleY);
        this.scaleY = scaleY;
    }

    public float getScale() {
        return scaleX == scaleY ? scaleX : null;
    }

    /**
     * Scale the entity
     *
     * @param scale the scale factor
     */
    public void setScale(float scale) {
        setScale(scale, scale);
    }

    public float getRotation() {
        return rotation;
    }

    /**
     * Rotate the entity
     *
     * @param degrees
     */
    public void setRotation(float degrees) {
        modelChanged = true;
        setRotating(degrees);
        this.rotation = degrees;
    }

    /**
     * @return true if this entity is attached in Scene
     */
    public boolean isRootInScene() {
        Entity entity = this;

        if (entity.inScene) {
            return true;
        }

        while (true) {
            if (entity.hasParent()) {
                entity = parent;
                if (entity.inScene) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * @return true if this entity is attached in HUD
     */
    public boolean isRootInHud() {
        Entity entity = this;

        if (entity.inHud) {
            return true;
        }

        while (true) {
            if (entity.hasParent()) {
                entity = parent;
                if (entity.inHud) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * @return true if is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set the entity visible or not
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * PRIVATE METHODS
     */
    private void makeModelTransformations() {

        if (modelChanged || parentChanged()) {
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

    private void setScaling(float scaleX, float scaleY) {
        Matrix.setIdentityM(mScaleMatrix, 0);
        Matrix.scaleM(mScaleMatrix, 0, scaleX, scaleY, 1);
    }

    private void setRotating(float degrees) {
        Matrix.setIdentityM(mRotationMatrix, 0);
        Matrix.setRotateM(mRotationMatrix, 0, degrees, 0, 0, 1);
    }
}
