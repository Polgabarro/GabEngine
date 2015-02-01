package com.pol.entities;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 01/02/15.
 */
public class Hud extends Entity {

    public Hud() {
        super(0, 0);
    }

    @Override
    public void attachChild(Entity entity) {
        super.attachChild(entity);
        entity.inHud = true;
    }

    @Override
    public void attachChild(Entity[] entities) {
        super.attachChild(entities);
        for (Entity i : entities) {
            i.inHud = true;
        }
    }

    @Override
    public void removeChild(Entity entity) {
        super.removeChild(entity);
        entity.inHud = false;
    }
}
