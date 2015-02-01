package com.pol.engine;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.pol.camera.Camera;
import com.pol.entities.Hud;
import com.pol.entities.Scene;
import com.pol.gabengine.BaseGabGame;
import com.pol.graphics.FPSCounter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 03/12/14.
 */
public class Engine implements GLSurfaceView.Renderer {


    public Scene scene = null;
    public BaseGabGame context;
    private Camera camera = null;
    private Hud hud = null;

    private float elapsedTime = 1L / 60L;
    private long lastTime = 0L;
    private FPSCounter fpsCounter = null;


    public Engine(BaseGabGame context) {
        this.context = context;
        context.onLoadOptions(this);
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     * <p/>
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("OpenGLError", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //For performance improvements
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glEnable(GLES20.GL_BLEND);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);
        GLES20.glDepthRangef(0.0f, 1.0f);
        //GLES20.glDepthMask( true );


        context.onLoadResources();
        context.onLoadEntities();
        scene = context.onLoadScene();
        elapsedTime = 0;
        lastTime = System.currentTimeMillis();


    }

    public void onDrawFrame(GL10 unused) {

        elapsedTime = (System.currentTimeMillis() - lastTime) * 1.0E-03f;
        lastTime = System.currentTimeMillis();
        if (fpsCounter != null) {
            fpsCounter.logFrame(elapsedTime);
        }
        if (scene != null) {
            /**
             * INPUTS
             */

            /**
             * UPDATE
             */
            scene.update(elapsedTime);
            camera.update(elapsedTime);
            if (hud != null) {
                hud.update(elapsedTime);
            }
            /**
             * RENDER
             */
            float[] color = scene.getBackground().getColor();
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            GLES20.glClearColor(color[0], color[1], color[2], 1);
            scene.render(camera.getmVPMatrix());
            if (hud != null) {
                hud.render(camera.getHudmVPMatrix());
            }
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        camera.onSurfaceChanged(width, height);
    }

    /**
     * PUBLIC METHODS
     */

    /**
     * @return the com.pol.camera of the engine
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Set the com.pol.camera of the engine
     *
     * @param camera of the engine
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Set a FPSCounter
     *
     * @param fpsCounter the fpsCounter
     */
    public void setFpsCounter(FPSCounter fpsCounter) {
        this.fpsCounter = fpsCounter;
    }

    public void setHud(Hud hud) {
        this.hud = hud;
    }

    public void removeHud() {
        this.hud = hud;
    }
}
