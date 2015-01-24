package com.pol.managers;

import android.view.MotionEvent;

import com.pol.entities.Shape;

import java.util.ArrayList;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 21/01/15.
 */
public class TouchManager {
    private static TouchManager ourInstance = null;

    private ArrayList<Shape> shapesToucheables;

    private TouchManager() {
        shapesToucheables = new ArrayList<Shape>();
    }

    public static TouchManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new TouchManager();
        }
        return ourInstance;
    }

    public static void init() {
        ourInstance = null;
    }

    public void addTouchDetection(Shape shape) {
        shapesToucheables.add(shape);
    }

    public void removeTouchDetection(Shape shape) {
        shapesToucheables.remove(shape);
    }

    public void detectTouchEvent(float posX, float posY, MotionEvent event) {

        for (Shape shape : shapesToucheables) {
            if (((shape.getX() + (shape.getWidth() * shape.getScaleX() / 2f) >= posX) && (shape.getX() - (shape.getWidth() * shape.getScaleX() / 2f) <= posX)) && ((shape.getY() + (shape.getHeight() * shape.getScaleY() / 2f) >= posY) && (shape.getY() - (shape.getHeight() * shape.getScaleY() / 2f) <= posY))) {
                shape.getOnTouchListener().onTouch(posX, posY, event);
            }
        }
    }

}
