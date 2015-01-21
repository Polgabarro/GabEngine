package com.pol.graphics;

import android.util.Log;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 29/12/14.
 */
public class FPSCounter {

    private int frames = 0;
    private int savedFrames = 0;
    private float sumTime = 0;
    private boolean isLogged;

    /**
     * The FPS Counter constructor, for default it make log
     */
    public FPSCounter() {
        this.isLogged = true;
    }

    /**
     * The FPS Counter constructor
     *
     * @param isLogged true if you want to log, false if not
     */
    public FPSCounter(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public void logFrame(float elapsedTime) {
        frames++;
        sumTime += elapsedTime;

        if (sumTime > 1f) {
            if (isLogged)
            Log.d("FPSCounter", "fps: " + frames);
            savedFrames =frames;
            frames = 0;
            sumTime = 0;
        }
    }

    /**
     * @return the saved frames
     */
    public int getFPS() {
        return savedFrames;
    }

    public void isLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
}
