package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.actions.ObtainedAction;

public interface Obtainable {

    ObtainedAction getObtainedAction();

    void obtainedBy(Actor actor);
}
