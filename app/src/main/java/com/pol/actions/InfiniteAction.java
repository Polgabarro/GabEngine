package com.pol.actions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public abstract class InfiniteAction extends Action {
    @Override
    public void update(float elapsedTime) {
        calcPosition(elapsedTime);
    }

    public abstract void calcPosition(float elapsedTime);
}
