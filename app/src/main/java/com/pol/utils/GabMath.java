package com.pol.utils;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/12/14.
 */
public class GabMath {


    public static void createCircumferenceVectors(int precision, float[] circumferenceVertex, short[] circumferenceIndex, float radius) {

        //int precision = (circumferenceVectors.length/3)-1;


        float dt = (float) ((2 * Math.PI) / (float) precision);


        for (int i = 0; i < precision; i++) {
            circumferenceVertex[3 * i] = getXCircumferencePoints(radius, dt * i);
            circumferenceVertex[3 * i + 1] = getYCircumferencePoints(radius, dt * i);
            circumferenceVertex[3 * i + 2] = 0;
        }
        circumferenceVertex[3 * (precision)] = 0;
        circumferenceVertex[3 * (precision) + 1] = 0;
        circumferenceVertex[3 * (precision) + 2] = 0;
        int count = 0;
        for (int i = 0; i < (precision); i++) {

            circumferenceIndex[count] = (short) i;
            if (i % 1 == 0 && i != 0) {
                count++;
                circumferenceIndex[count] = (short) precision;
                count++;
                circumferenceIndex[count] = (short) i;
            }
            count++;
        }
        circumferenceIndex[count] = 0;
        circumferenceIndex[count + 1] = (short) precision;

    }

    private static float getXCircumferencePoints(float radius, float k) {
        return (float) (radius * Math.cos(k));
    }

    private static float getYCircumferencePoints(float radius, float k) {
        return (float) (radius * Math.sin(k));
    }

}
