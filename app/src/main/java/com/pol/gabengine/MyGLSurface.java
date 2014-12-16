package com.pol.gabengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 03/12/14.
 */
public class MyGLSurface extends GLSurfaceView {
    public MyGLSurface(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        // Set the Renderer for drawing on the GLSurfaceView

        setRenderer(new MyRenderer());

    }
}
