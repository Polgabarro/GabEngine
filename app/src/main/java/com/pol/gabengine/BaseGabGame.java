package com.pol.gabengine;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.pol.engine.Engine;
import com.pol.entities.Scene;
import com.pol.graphics.Shader;


public abstract class BaseGabGame extends Activity implements GameInterface{

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
        onLoadOptions();
        mGLView = new GabGLSurface(this);
        mGLView.setEGLContextClientVersion(2);
        engine =  new Engine(this);
        mGLView.setRenderer(engine);
        setContentView(mGLView);
    }
    /**
     * PUBLIC METHODS
     */
    public abstract void onLoadOptions();
    public abstract void onLoadResources();
    public abstract void onLoadEntities();
    public abstract Scene onLoadScene();


    /**
     * PRIVATE METHODS
     */
    private void init(){
        Shader.init(this);
    }

}
