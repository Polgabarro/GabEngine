package com.pol.graphics;

import android.util.Log;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 29/12/14.
 */
/*public class FPSCounter {

    private int frames = 0;
    private float sumTime=0;


    public void logFrame(float elapsedTime) {
        frames++;
        sumTime+=elapsedTime;

        if(sumTime>1f) {
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            sumTime=0;
        }
    }

}*/
public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;

    public void logFrame() {
        frames++;
        if(System.nanoTime() - startTime >= 1000000000) {
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            startTime = System.nanoTime();
        }
    }
}
