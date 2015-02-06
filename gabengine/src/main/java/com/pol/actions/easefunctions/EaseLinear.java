package com.pol.actions.easefunctions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public class EaseLinear extends EaseFunction {

    public static EaseLinear easeLinear = null;

    public static EaseLinear getInstance() {
        if (easeLinear == null) {
            easeLinear = new EaseLinear();
        }
        return easeLinear;
    }

    @Override
    public float getValue(float percentage) {
        return percentage;
    }
}
