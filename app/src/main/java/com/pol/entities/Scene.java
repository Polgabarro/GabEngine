package com.pol.entities;

import com.pol.entities.background.Background;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class Scene extends Entity {

    private Background background;

    public Scene() {
        super(0, 0);
        background = new Background(0, 0, 0);
    }

    public Scene(Background background) {
        super(0, 0);
        this.background = background;
    }

    /**
     * Attach a child
     *
     * @param entity The entity
     */
    @Override
    public void attachChild(Entity entity) {
        super.attachChild(entity);
        entity.inScene = true;
    }

    /**
     * Attach a child
     *
     * @param entities Array of entities
     */
    @Override
    public void attachChild(Entity[] entities) {
        super.attachChild(entities);
        for (Entity entity : entities) {
            entity.inScene = true;
        }
    }

    /**
     * Remove the child
     *
     * @param entity
     */
    @Override
    public void detachChild(Entity entity) {
        super.detachChild(entity);
        entity.inScene = false;
    }

    public Background getBackground() {
        return background;
    }

    /**
     * Set a background to the scene
     *
     * @param background
     */
    public void setBackground(Background background) {
        this.background = background;
    }

}


