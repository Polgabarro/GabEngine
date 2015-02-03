package com.pol.gabengine;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.pol.engine.Engine;
import com.pol.entities.Hud;
import com.pol.entities.Scene;
import com.pol.entities.SpriteCreator;
import com.pol.entities.TextCreator;
import com.pol.graphics.Shader;
import com.pol.graphics.textures.FontFactory;
import com.pol.graphics.textures.TextureFactory;
import com.pol.managers.TouchManager;
import com.pol.utils.io.OnTouchListener;


public abstract class BaseGabGame extends Activity implements GameInterface {

    public float statusBarSize;
    /**
     * VARIABLES
     */
    private Engine engine;
    private GLSurfaceView mGLView;
    private boolean isOnTouchListener = false;

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

        statusBarSize = getStatusBarHeight();/*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        if (this instanceof OnTouchListener) {
            isOnTouchListener = true;
        }
        setContentView(mGLView);

    }

    /**
     * @return the engine
     */
    public Engine getEngine() {
        return engine;
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
        FontFactory.init(this);
        SpriteCreator.init();
        TouchManager.init();
        TextCreator.init();

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

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isOnTouchListener) {
            ((OnTouchListener) this).onTouch(event.getX() - engine.getCamera().getResolutionX() / 2f + engine.getCamera().getX(), -((event.getY() - statusBarSize) - engine.getCamera().getResolutionY() / 2f) + engine.getCamera().getY(), event);
        }
        TouchManager.getInstance().detectTouchEvent(event.getX() - engine.getCamera().getResolutionX() / 2f, -((event.getY() - statusBarSize) - engine.getCamera().getResolutionY() / 2f), event, engine.getCamera());

        return false;
    }

    /**
     * Set a HUD
     *
     * @param hud
     */
    public void setHud(Hud hud) {
        engine.setHud(hud);
    }

    /**
     * Remove the Hud
     */
    public void removeHud() {
        engine.removeHud();
    }
}
