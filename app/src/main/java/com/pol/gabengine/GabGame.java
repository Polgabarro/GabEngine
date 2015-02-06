package com.pol.gabengine;

import com.pol.engine.Engine;
import com.pol.entities.Scene;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/01/15.
 */
public abstract class GabGame extends BaseGabGame {

    @Override
    public void onLoadOptions(Engine engine) {
        config(engine);
    }

    @Override
    public void onLoadResources() {
    }

    @Override
    public void onLoadEntities() {

    }

    @Override
    public Scene onLoadScene() {
        return init();
    }

    public abstract void config(Engine engine);

    public abstract Scene init();

}
