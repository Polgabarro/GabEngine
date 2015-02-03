package com.pol.entities.updateModifier;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 15/01/15.
 */
public abstract class TimeElapsedListener implements UpdateListener {
    private float timeElapsed;
    private float time;
    private boolean made = false;

    public TimeElapsedListener(float timeElapsed) {
        this.timeElapsed = timeElapsed;
        time = 0;
    }

    @Override
    public void update(float elapsedTime) {
        time += elapsedTime;
        if (time >= timeElapsed && !made) {
            onTimeElapsed();
            made = true;
        }
    }

    /**
     * Run this method when pass a time
     */
    public abstract void onTimeElapsed();
}
