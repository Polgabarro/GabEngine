package com.pol.gabengine;

import com.pol.engine.Engine;
import com.pol.entities.Scene;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 17/12/14.
 */
public interface GameInterface {
    public void onLoadOptions(Engine engine);

    public void onLoadResources();

    public void onLoadEntities();

    public Scene onLoadScene();
}
