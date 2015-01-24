package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 23/01/15.
 */
public class ScaleXAction extends FiniteAction {
    float initScaleX, finalScaleX, diffScaleX;

    public ScaleXAction(float initScaleX, float finalScaleX, float time, boolean loop) {
        this.initScaleX = initScaleX;
        this.finalScaleX = finalScaleX;
        this.diffScaleX = finalScaleX - initScaleX;
        this.totalTime = time;
        this.loop = loop;
    }

    public ScaleXAction(float initScaleX, float finalScaleX, float time, boolean loop, EaseFunction easeFunction) {
        this.initScaleX = initScaleX;
        this.finalScaleX = finalScaleX;
        this.totalTime = time;
        this.loop = loop;
        this.easeFunction = easeFunction;
    }

    @Override
    protected void calcModification() {
        entity.setScaleX(initScaleX + diffScaleX * percentage);
    }
}
