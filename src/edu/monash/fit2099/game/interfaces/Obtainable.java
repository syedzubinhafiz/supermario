package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.actions.ObtainedAction;


/**
 * Interface for Obtainable Items
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.interfaces
 */
public interface Obtainable {


    /**
     * Method to be implemented, responsible for getting the ObtainedAction of the Obtainable Item
     * @return ObtainAction instance
     */
    ObtainedAction getObtainedAction();


    /**
     * Method to be implemented, responsible for what happens when obtainable item gets obtained by the actor, responsible for any changes to player.
     * @param actor Actor obtaining the item
     */
    void obtainedBy(Actor actor);
}
