package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;
import com.pol.camera.Camera;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 23/01/15.
 */
public class ZoomAction extends FiniteAction {
    float initScaleX, finalScaleX, diffScaleX, initScaleY, finalScaleY, diffScaleY;

    public ZoomAction(float initScale, float finalScale, float time, boolean loop) {
        this(initScale, initScale, finalScale, finalScale, time, loop);
    }

    public ZoomAction(float initScale, float finalScale, float time, boolean loop, EaseFunction easeFunction) {
        this(initScale, initScale, finalScale, finalScale, time, loop, easeFunction);
    }

    public ZoomAction(float initScaleX, float initScaleY, float finalScaleX, float finalScaleY, float time, boolean loop) {
        this.initScaleX = initScaleX;
        this.initScaleY = initScaleY;
        this.finalScaleX = finalScaleX;
        this.finalScaleY = finalScaleY;
        this.diffScaleX = finalScaleX - initScaleX;
        this.diffScaleY = finalScaleY - initScaleY;
        this.totalTime = time;
        this.loop = loop;
        this.loop = loop;
    }

    public ZoomAction(float initScaleX, float initScaleY, float finalScaleX, float finalScaleY, float time, boolean loop, EaseFunction easeFunction) {
        this.initScaleX = initScaleX;
        this.initScaleY = initScaleY;
        this.finalScaleX = finalScaleX;
        this.finalScaleY = finalScaleY;
        this.diffScaleX = finalScaleX - initScaleX;
        this.diffScaleY = finalScaleY - initScaleY;
        this.totalTime = time;
        this.loop = loop;
        this.easeFunction = easeFunction;
    }

    @Override
    protected void calcModification() {
        ((Camera) entity).setZoom(initScaleX + diffScaleX * percentage);
    }
}
