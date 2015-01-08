package com.pol.actions;

import com.pol.actions.easefunctions.EaseFunction;
import com.pol.actions.easefunctions.EaseLinear;
import com.pol.entities.Entity;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 08/01/15.
 */
public abstract class Action
{
    protected boolean loop;
    protected boolean infinite;
    protected float time=0;
    protected float totalTime;
    protected float percentatge=0;
    protected EaseFunction easeFunction;
    Entity entity = null;

    /*
     * CONSTRUCTOR
     */
    public Action(){
        time=0;
        percentatge=0;
        easeFunction = new EaseLinear();
        loop=false;
    }
    public Action(boolean loop, EaseFunction easeFunction){
        this();
        this.loop=loop;
        this.easeFunction=easeFunction;
    }

    /*
     * PUBLIC METHODS
     */
    public void update(float elapsedTime){
        time+=elapsedTime;
        if(time>=totalTime){

            if(loop){
                time=0;
            }
            else{
                return;
            }
        }
        calcPercentage();
        calcModification();


    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public void removeEntity(){
        this.entity=null;
    }
    /*
     * PROTECTED METHODS
     */
    protected void calcPercentage(){
        percentatge = easeFunction.calcPercentage(time,totalTime);
    }
    protected abstract void calcModification();


}
