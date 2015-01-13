package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */

public class MoveToAction extends FiniteAction {

    float initX, initY, diffX, diffY;
    /*
     * CONSTRUCTOR
     */

    /**
     * Constructs a new moveToAction
     *
     * @param initX  the initial X position
     * @param initY  the initial Y position
     * @param finalX the final X position
     * @param finalY the final Y position
     * @param loop   the action
     * @param time   the time to make the move
     */
    public MoveToAction(float initX, float initY, float finalX, float finalY, float time, boolean loop) {
        super();
        this.initX = initX;
        this.initY = initY;
        this.diffX = finalX - initX;
        this.diffY = finalY - initY;

        this.totalTime = time;
        this.loop = loop;
    }

    /**
     * Constructs an new moveToAction
     *
     * @param initX        the initial X position
     * @param initY        the initial Y position
     * @param finalX       the final X position
     * @param finalY       the final Y position
     * @param time         the time to make the move
     * @param loop         the action
     * @param easeFunction the function of the move
     */
    public MoveToAction(float initX, float initY, float finalX, float finalY, float time, boolean loop, EaseFunction easeFunction) {
        this.initX = initX;
        this.initY = initY;
        this.diffX = finalX - initX;
        this.diffY = finalY - initY;
        this.totalTime = time;
        this.easeFunction = easeFunction;
    }
    /*
     * PUBLIC
     */


    /*
     *PROTECTED METHODS
     */

    @Override
    protected void calcModification() {
        entity.setPosition(initX + diffX * percentage, initY + diffY * percentage);
    }

}
