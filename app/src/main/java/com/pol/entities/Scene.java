package com.pol.entities;

import com.pol.entities.background.Background;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 16/12/14.
 */
public class Scene extends Entity {

    private Background background;

    public Scene() {
        super(0, 0);
        background = new Background(0, 0, 0);
    }

    public Scene(Background background) {
        super(0, 0);
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

}


