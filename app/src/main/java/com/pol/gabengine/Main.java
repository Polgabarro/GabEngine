package com.pol.gabengine;

import com.pol.camera.Camera;
import com.pol.camera.CameraOrientation;
import com.pol.engine.Engine;
import com.pol.entities.Scene;
import com.pol.entities.Shape;
import com.pol.entities.ShapeCreator;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 17/12/14.
 */
public class Main extends BaseGabGame {

    Scene scene;
    Shape testShape, testShape2;

    @Override
    public void onLoadOptions(Engine engine) {
        Camera camera = new Camera(768, 1184, CameraOrientation.PORTRAIT_FIXED);
        engine.setCamera(camera);
    }

    @Override
    public void onLoadResources() {

    }

    @Override
    public void onLoadEntities() {
        scene = new Scene();
        testShape = ShapeCreator.createRectangle(300, 300, 100, 100);
        testShape2 = ShapeCreator.createRectangle(-100, -300, 200, 200, new float[]{0.6f, 1f, 0f});

    }

    @Override
    public Scene onLoadScene() {
        scene.attachChild(testShape);
        scene.attachChild(testShape2);
        //testShape.attachChild(testShape);
        return scene;
    }
}
