package com.pol.actions;

import com.pol.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 24/01/15.
 */
public class ParallelAction extends Action {

    private ArrayList<Action> actions;

    public ParallelAction(Action... actions) {
        this.actions = new ArrayList<Action>(Arrays.asList(actions));
        finished = false;
    }

    @Override
    public boolean update(float elapsedTime) {
        if (!finished) {
            for (Action action : actions) {
                action.update(elapsedTime);
                finished = action.finished & finished;
            }
            return finished;
        } else {
            return finished;
        }
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
        for (Action action : actions) {
            action.setEntity(entity);
        }
    }

    public void reset() {
        finished = false;
        for (Action action : actions) {
            action.reset();
        }
    }

}
