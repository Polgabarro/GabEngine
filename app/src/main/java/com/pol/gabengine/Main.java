package com.pol.gabengine;

import com.pol.entities.Scene;
import com.pol.entities.Shape;
import com.pol.entities.ShapeCreator;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 17/12/14.
 */
public class Main extends BaseGabGame{

    Scene scene;
    Shape testshape;

    @Override
    public void onLoadOptions() {

    }

    @Override
    public void onLoadResources() {

    }

    @Override
    public void onLoadEntities() {
        scene = new Scene();
        testshape = ShapeCreator.createRectangle(0,0,1,1);
    }

    @Override
    public Scene onLoadScene() {
        scene.attachChild(testshape);
        return scene;
    }
}
