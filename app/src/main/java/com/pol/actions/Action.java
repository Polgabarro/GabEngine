package com.pol.actions;

import com.pol.entities.Entity;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public abstract class Action {
    protected Entity entity = null;
    protected boolean started = false;

    /*
     * CONSTRUCTOR
     */
    public Action() {
    }

    /*
     * PUBLIC METHODS
     */
    public abstract void update(float elapsedTime);


    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void removeEntity() {
        this.entity = null;
    }

    /**
     * Reset the action
     */
    public void reset() {
        started = false;
    }

}
