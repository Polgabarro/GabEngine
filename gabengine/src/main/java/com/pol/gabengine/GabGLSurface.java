package com.pol.gabengine;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 03/12/14.
 */
public class GabGLSurface extends GLSurfaceView {
    public GabGLSurface(Context context) {
        super(context);
        super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

    }
}
