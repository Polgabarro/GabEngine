package com.pol.gabengine;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.pol.graphics.Shader;


public class MainOpenGL extends Activity {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        Shader.init(this);

        mGLView = new MyGLSurface(this);
        setContentView(mGLView);
/*
        setContentView(R.layout.activity_main_open_gl);

        GLSurfaceView mySurface =  (GLSurfaceView)findViewById(R.id.surfaceView);
        mySurface.setEGLContextClientVersion(2);
        mySurface.setRenderer(new MyRenderer());
*/


    }
}
