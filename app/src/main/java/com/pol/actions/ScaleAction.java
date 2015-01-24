package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 23/01/15.
 */
public class ScaleAction extends FiniteAction {
    float initScaleX, finalScaleX, diffScaleX, initScaleY, finalScaleY, diffScaleY;

    public ScaleAction(float initScale, float finalScale, float time, boolean loop) {
        this(initScale, initScale, finalScale, finalScale, time, loop);
    }

    public ScaleAction(float initScale, float finalScale, float time, boolean loop, EaseFunction easeFunction) {
        this(initScale, initScale, finalScale, finalScale, time, loop, easeFunction);
    }

    public ScaleAction(float initScaleX, float initScaleY, float finalScaleX, float finalScaleY, float time, boolean loop) {
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

    public ScaleAction(float initScaleX, float initScaleY, float finalScaleX, float finalScaleY, float time, boolean loop, EaseFunction easeFunction) {
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
        entity.setScale(initScaleX + diffScaleX * percentage, initScaleY + diffScaleY * percentage);
    }
}
