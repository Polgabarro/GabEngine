package com.pol.gabengine;

import com.pol.camera.Camera;
import com.pol.camera.CameraOrientation;
import com.pol.engine.Engine;
import com.pol.entities.Scene;
import com.pol.entities.Shape;
import com.pol.entities.ShapeCreator;
import com.pol.entities.background.Background;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 17/12/14.
 */
public class Main extends BaseGabGame {

    Scene scene;
    Shape testShape, testShape2, testShape3;

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
        scene = new Scene(new Background(1, 0, 1));
        //testShape = ShapeCreator.createRectangle(200, 100, 200, 200);
        //testShape.setX(0);
        //testShape.setY(0);
        //testShape.setPosition(-250,100);
        //testShape2 = ShapeCreator.createRectangle(-200, 0, 200, 200, new float[]{0.6f, 1f, 0f});
        testShape3 = ShapeCreator.createSphere(0,0,100);
    }

    @Override
    public Scene onLoadScene() {
        scene.attachChild(testShape3);
        //testShape.attachChild(testShape2);
        //scene.attachChild(testShape3);
        //
        //testShape.setPosition(-200, 100);
        return scene;
    }
}
