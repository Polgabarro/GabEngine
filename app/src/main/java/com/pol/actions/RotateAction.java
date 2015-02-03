package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 23/01/15.
 */
public class RotateAction extends FiniteAction {
    float initRotate, finalRotate, diffRotate;

    public RotateAction(float initRotate, float finalRotate, float time, boolean loop) {
        this.initRotate = initRotate;
        this.finalRotate = finalRotate;
        this.diffRotate = finalRotate - initRotate;
        this.totalTime = time;
        this.loop = loop;
    }

    public RotateAction(float initRotate, float finalRotate, float time, boolean loop, EaseFunction easeFunction) {
        this.initRotate = initRotate;
        this.finalRotate = finalRotate;
        this.diffRotate = finalRotate - initRotate;
        this.totalTime = time;
        this.loop = loop;
        this.easeFunction = easeFunction;
    }

    @Override
    protected void calcModification() {
        entity.setRotation(initRotate + diffRotate * percentage);
    }
}
