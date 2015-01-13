package com.pol.gabengine;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import com.pol.engine.Engine;
import com.pol.entities.Scene;
import com.pol.graphics.Shader;
import com.pol.graphics.textures.TextureFactory;


public abstract class BaseGabGame extends Activity implements GameInterface {

    /**
     * VARIABLES
     */
    Engine engine;
    private GLSurfaceView mGLView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Init Shader
        init();
        mGLView = new GabGLSurface(this);
        mGLView.setEGLContextClientVersion(2);
        engine = new Engine(this);
        setOrientation(engine);
        mGLView.setRenderer(engine);

        if (engine.getCamera() == null) {
            Log.e("Camera Error", "Attach a com.pol.camera in the Engine");
            System.exit(0);
        }

        //mGLView.getHolder().setFixedSize(engine.getCamera().getResolutionX(), engine.getCamera().getResolutionY());

        setContentView(mGLView);


    }


    /**
     * PUBLIC METHODS
     */
    public abstract void onLoadOptions(Engine engine);

    public abstract void onLoadResources();

    public abstract void onLoadEntities();

    public abstract Scene onLoadScene();


    /**
     * PRIVATE METHODS
     */
    private void init() {
        Shader.init(this);
        TextureFactory.init(this);
    }

    private void setOrientation(Engine engine) {
        int ORIENTATION_MODE = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        switch (engine.getCamera().getCameraOrientation()) {
            case PORTRAIT_FIXED:
                ORIENTATION_MODE = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            case LANDSCAPE_FIXED:
                ORIENTATION_MODE = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                break;
            case SENSOR_MODE:
                ORIENTATION_MODE = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
                break;
        }

        setRequestedOrientation(ORIENTATION_MODE);

    }


}
