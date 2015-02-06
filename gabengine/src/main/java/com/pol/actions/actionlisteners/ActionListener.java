package com.pol.actions.actionlisteners;

import com.pol.actions.FiniteAction;
import com.pol.entities.Entity;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 12/01/15.
 */
public interface ActionListener {
    boolean autoRemove = false;

    public void onActionBegin(Entity actioned, FiniteAction action);

    public void onActionOn(Entity actioned, FiniteAction action);

    public void onActionEnd(Entity actioned, FiniteAction action);

}
