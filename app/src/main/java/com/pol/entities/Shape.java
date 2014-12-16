package com.pol.entities;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class Shape extends Entity {

    /**
     * VARIABLES
     */
    private boolean containFigure;
    protected float squareCoords[];
    protected short squareIndex[];
    protected float color[]{1.0,1.0,1.0,1.0};

    private int mProgram;

    /**
     * CONSTRUCTORS
     */
    public Shape(float x, float y) {
        super(x, y);
    }

    /**
     * PUBLIC METHODS
     */
    public   void setColor(float RED, float GREEN, float BLUE) {

    }
    /**
     * PROTECTED METHODS
     */
    public void setShader(int idShader){
        this.mProgram = idShader;
    }

    /**
     * PRIVATE METHODS
     */

}
