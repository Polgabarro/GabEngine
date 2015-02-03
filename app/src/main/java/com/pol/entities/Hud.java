package com.pol.entities;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 01/02/15.
 */
public class Hud extends Entity {

    public Hud() {
        super(0, 0);
    }

    /**
     * Attach a child
     *
     * @param entity The entity
     */
    @Override
    public void attachChild(Entity entity) {
        super.attachChild(entity);
        entity.inHud = true;
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
            entity.inHud = true;
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
        entity.inHud = false;
    }
}
