package com.pol.actions.easefunctions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public abstract class EaseFunction {
    public float calcPercentage(float time, float totalTime) {
        return getValue(time / totalTime);
    }

    public abstract float getValue(float percentage);
}


