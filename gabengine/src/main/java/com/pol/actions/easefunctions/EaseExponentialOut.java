package com.pol.actions.easefunctions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public class EaseExponentialOut extends EaseFunction {

    public static EaseExponentialOut easeLinear = null;

    public static EaseExponentialOut getInstance() {
        if (easeLinear == null) {
            easeLinear = new EaseExponentialOut();
        }
        return easeLinear;
    }

    @Override
    public float getValue(float percentage) {
        return (percentage == 1) ? 1 : (-(float) Math.pow(2, -10 * percentage) + 1);
    }
}
