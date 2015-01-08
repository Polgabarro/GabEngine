package com.pol.gabengine;

import com.pol.actions.MoveToAction;
import com.pol.camera.Camera;
import com.pol.camera.CameraOrientation;
import com.pol.engine.Engine;
import com.pol.entities.Scene;
import com.pol.entities.Shape;
import com.pol.entities.ShapeCreator;
import com.pol.entities.background.Background;
import com.pol.graphics.FPSCounter;

import java.util.Random;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 17/12/14.
 */
public class Main extends BaseGabGame {

    Scene scene;
    Shape testShape, testShape2, testShape3, testShape4;
    Shape[] test = new Shape[3000];

    @Override
    public void onLoadOptions(Engine engine) {
        Camera camera = new Camera(768, 1184, CameraOrientation.PORTRAIT_FIXED);
        engine.setCamera(camera);
        engine.setFpsCounter(new FPSCounter());
    }

    @Override
    public void onLoadResources() {

    }

    @Override
    public void onLoadEntities() {
        scene = new Scene(new Background(0, 0, 1));
        //testShape = ShapeCreator.createRectangle(200, 100, 200, 200);
        //testShape.setX(0);
        //testShape.setY(0);
        //testShape.setPosition(-250,100);
        //testShape2 = ShapeCreator.createRectangle(-200, 0, 200, 200, new float[]{0.6f, 1f, 0f});
        testShape3 = ShapeCreator.createCircle(0, 0, 100);
        testShape3.setColor(1,0,0);
        MoveToAction moveToAction = new MoveToAction(-300,0,300,300,4,false);
        testShape3.addAction(moveToAction);
        //testShape4 = ShapeCreator.createPolygon(200,200,5,100, new float[]{1,0,0});
        /*
        for(int i=0;i<test.length;i++){
            test[i]=ShapeCreator.createRectangle(randInt(-768/2, 768/2),randInt(-1184/2,1184/2),100,100,randColor());
        }*/

    }

    @Override
    public Scene onLoadScene() {
        scene.attachChild(testShape3);
        //scene.attachChild(testShape4);
        //scene.attachChild(test);
        //testShape.attachChild(testShape2);
        //scene.attachChild(testShape3);
        //
        //testShape.setPosition(-200, 100);
        return scene;
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public static float[] randColor(){
        Random rand = new Random();
        float[] color = new float[3];
        color[0] = rand.nextFloat();
        color[1] = rand.nextFloat();
        color[2] = rand.nextFloat();
        return color;
    }
}

