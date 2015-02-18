package com.pol.actions;

import com.pol.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 24/01/15.
 */
public class ListAction extends Action {

    private ArrayList<Action> actions;
    private int actionNum = 0;
    private boolean loop = false;

    /**
     * Create a Action list of entities
     *
     * @param actions
     */
    public ListAction(Action... actions) {
        this.actions = new ArrayList<Action>(Arrays.asList(actions));
        finished = false;


    }

    /**
     * Create a Action list of entities
     *
     * @param loop    true if is a loop
     * @param actions
     */
    public ListAction(boolean loop, Action... actions) {
        this.actions = new ArrayList<Action>(Arrays.asList(actions));
        finished = false;
        this.loop = loop;
    }

    @Override
    public boolean update(float elapsedTime) {
        if (!finished) {
            if (!actions.get(actionNum).update(elapsedTime)) {
                return false;
            } else {
                actionNum++;
                if (actionNum >= actions.size()) {
                    finished = true;
                    if (loop) {
                        actionNum = 0;
                        finished = false;
                        for (Action action : actions) {
                            action.reset();
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }

        } else {
            return true;
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
        actionNum = 0;
        finished = false;
        for (Action action : actions) {
            action.reset();
        }

    }

}
