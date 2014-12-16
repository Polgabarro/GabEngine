package com.pol.entities;

import java.util.ArrayList;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/12/14.
 */
public class Entity {

    /**
     * VARIABLES
     */
    private float x, y;
    private ArrayList<Entity> entities;

    /**
     * CONSTRUCTORS
     */
    public Entity() {
        entities = new ArrayList<>();
    }

    public Entity(float x, float y) {
        this();
        this.x = x;
        this.y = y;
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
    }

    /**
     * Attach a child
     *
     * @param entity
     */
    public void attachChild(Entity entity) {
        entities.add(entity);
    }

    /**
     * Remove the child
     *
     * @param entity
     */
    public void removeChild(Entity entity) {
        entity.removeChild(entity);
    }


    public void update() {

    }

    public void render() {

    }

    /**
     * PRIVATE METHODS
     */


}
