package com.pol.actions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public abstract class InfiniteAction extends Action {
    @Override
    public boolean update(float elapsedTime) {
        calcPosition(elapsedTime);
        return false;
    }

    public abstract void calcPosition(float elapsedTime);
}
