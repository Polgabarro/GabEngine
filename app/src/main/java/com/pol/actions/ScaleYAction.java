package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 23/01/15.
 */
public class ScaleYAction extends FiniteAction {
    float initScaleY, finalScaleY, diffScaleY;

    public ScaleYAction(float initScaleY, float finalScaleY, float time, boolean loop) {
        this.initScaleY = initScaleY;
        this.finalScaleY = finalScaleY;
        this.diffScaleY = finalScaleY - initScaleY;
        this.totalTime = time;
        this.loop = loop;
    }

    public ScaleYAction(float initScaleY, float finalScaleY, float time, boolean loop, EaseFunction easeFunction) {
        this.initScaleY = initScaleY;
        this.finalScaleY = finalScaleY;
        this.diffScaleY = finalScaleY - initScaleY;
        this.totalTime = time;
        this.loop = loop;
        this.easeFunction = easeFunction;
    }

    @Override
    protected void calcModification() {
        entity.setScaleY(initScaleY + diffScaleY * percentage);
    }
}
