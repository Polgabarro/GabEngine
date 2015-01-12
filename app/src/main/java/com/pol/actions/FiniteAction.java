package com.pol.actions;

import com.pol.actions.actionlisteners.ActionListener;
import com.pol.actions.easefunctions.EaseFunction;
import com.pol.actions.easefunctions.EaseLinear;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public abstract class FiniteAction extends Action {
    protected boolean loop;
    protected float time = 0;
    protected float totalTime;
    protected float percentage = 0;
    protected EaseFunction easeFunction;
    protected ActionListener actionListener = null;
    protected boolean finished = false;

    /*
     * CONSTRUCTOR
     */

    public FiniteAction() {
        this.easeFunction = EaseLinear.getInstance();
    }

    /*
     * PUBLIC METHODS
     */
    public void update(float elapsedTime) {
        if (finished)
            return;

        if (!started && actionListener != null) {
            actionListener.onActionBegin(entity, this);
            started = true;
        }
        time += elapsedTime;
        if (time >= totalTime) {

            if (loop) {
                time = 0;
                if (actionListener != null)
                    actionListener.onActionEnd(entity, this);
            } else {
                if (actionListener != null && !finished) {
                    finished = true;
                    actionListener.onActionEnd(entity, this);
                    if (actionListener.autoRemove) {
                        actionListener = null;
                    }
                }
                return;
            }
        }
        calcPercentage();
        calcModification();
        if (actionListener != null)
            actionListener.onActionOn(entity, this);


    }

    /**
     * Set an actionListener
     *
     * @param actionListener the actionListener
     */
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * Remove an actionListener
     */
    public void removeActionListeners() {
        this.actionListener = null;
    }

    /**
     * @return if is a loop
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * @return total time
     */
    public float getTotalTime() {
        return totalTime;
    }

    /**
     * @return the actual percentage of the action
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * @return the time
     */
    public float getTime() {
        return time;
    }

    /**
     * @return the easeFunction
     */
    public EaseFunction getEaseFunction() {
        return easeFunction;
    }

    /**
     * Set a new easeFunction
     *
     * @param easeFunction the easeFunction
     */
    public void setEaseFunction(EaseFunction easeFunction) {
        this.easeFunction = easeFunction;
    }

    /**
     * Reset the action
     */
    public void reset() {
        started = false;
        finished = false;
        time = 0;
        percentage = 0;
    }

    /*
     * PROTECTED METHODS
     */
    protected void calcPercentage() {
        percentage = easeFunction.calcPercentage(time, totalTime);
    }

    protected abstract void calcModification();


}
