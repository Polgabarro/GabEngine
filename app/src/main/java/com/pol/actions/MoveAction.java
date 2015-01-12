package com.pol.actions;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public class MoveAction extends InfiniteAction {

    private float velocityX, velocityY;
    private float accelerationX, accelerationY;

    /*
     * CONSTRUCTORS
     */

    public MoveAction(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.accelerationX = 1.0f;
        this.accelerationY = 1.0f;
    }

    public MoveAction(float velocityX, float velocityY, float accelerationX, float accelerationY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
    }
    /*
     * PUBLIC METHODS
     */

    /**
     * @return the velocityX
     */
    public float getVelocityX() {
        return velocityX;
    }

    /**
     * Set the velocityX
     *
     * @param velocityX is the velocityX
     */
    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * @return the velocityY
     */
    public float getVelocityY() {
        return velocityY;
    }

    /**
     * Set the velocityY
     *
     * @param velocityY is the velocityY
     */
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * @return the accelerationX
     */
    public float getAccelerationX() {
        return accelerationX;
    }

    /**
     * Set the accelerationX
     *
     * @param accelerationX is the acceleration
     */
    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    /**
     * @return the accelerationY
     */
    public float getAccelerationY() {
        return accelerationY;
    }

    /**
     * Set the accelerationY
     *
     * @param accelerationY
     */
    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    /**
     * Set the velocity
     *
     * @param velocityX is the velocityX
     * @param velocityY is the velocityY
     */
    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Set the acceleration
     *
     * @param accelerationX is the accelerationX
     * @param accelerationY is the accelerationY
     */
    public void setAcceleration(float accelerationX, float accelerationY) {
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
    }


    /*
         * PROTECTED METHODS
         */
    @Override
    public void calcPosition(float elapsedTime) {

        velocityX = velocityX + accelerationX * elapsedTime;
        velocityY = velocityY + accelerationY * elapsedTime;

        entity.setX(entity.getX() + velocityX * elapsedTime);
        entity.setY(entity.getY() + velocityY * elapsedTime);
    }
}
