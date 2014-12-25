package com.pol.engine;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.pol.camera.Camera;
import com.pol.entities.Scene;
import com.pol.gabengine.BaseGabGame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 03/12/14.
 */
public class Engine implements GLSurfaceView.Renderer {

    //Shape testshape;
    public Scene scene = null;
    public BaseGabGame context;
    private Camera camera = null;

    public Engine(BaseGabGame context) {
        this.context = context;
        context.onLoadOptions(this);
    }


    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //For performance improvements
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        context.onLoadResources();
        context.onLoadEntities();
        scene = context.onLoadScene();
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(0, 0, 1, 1);
        if (scene != null) {
            scene.render(camera.getmVPMatrix());
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        camera.onSurfaceChanged(width, height);
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
}
