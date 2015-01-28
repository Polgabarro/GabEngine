package com.pol.entities.text;


import com.pol.entities.Shape;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 26/01/15.
 */
public class Text extends Shape {
    private String sText;

    /**
     * CONSTRUCTORS
     *
     * @param x
     * @param y
     */
    protected Text(float x, float y, String sText) {
        super(x, y);
        this.sText = sText;
    }


}
