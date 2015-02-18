package com.pol.actions;

import com.pol.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 24/01/15.
 */
public class ParallelAction extends Action {

    private ArrayList<Action> actions;
    private boolean loop = false;

    /**
     * Create a parallel action
     *
     * @param actions
     */
    public ParallelAction(Action... actions) {
        this.actions = new ArrayList<Action>(Arrays.asList(actions));
        finished = false;
    }

    /**
     * Create a parallel action
     *
     * @param loop    true if is a loop
     * @param actions
     */
    public ParallelAction(boolean loop, Action... actions) {
        this.actions = new ArrayList<Action>(Arrays.asList(actions));
        finished = false;
        this.loop = loop;
    }

    @Override
    public boolean update(float elapsedTime) {
        if (!finished) {
            for (Action action : actions) {
                action.update(elapsedTime);
                finished = action.finished & finished;
            }
            if (loop && finished) {
                finished = false;
                for (Action action : actions) {
                    action.reset();
                }
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
