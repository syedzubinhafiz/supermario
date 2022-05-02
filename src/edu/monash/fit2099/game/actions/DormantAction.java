package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Koopa;
import edu.monash.fit2099.game.enums.Status;

/**
 * Special Action that allows an actor to become dormant
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.actions
 */
public class DormantAction extends Action {

    /**
     * Target actor to become dormant
     */
    protected Actor target;

    /**
     * New display character of dormant actor
     */
    protected char newDisplayChar;


    /**
     * Constructor
     * @param toBeDormantActor actor to become dormant
     * @param character character of the actor when dormant
     */
    public DormantAction( Actor toBeDormantActor , char character){
        target = toBeDormantActor;
        newDisplayChar = character;
    }

    @Override
    /**
     * Executes the dormant action, where it adds the dormant capability status to actor and changes the display character.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    public String execute(Actor actor, GameMap map) {
        target.addCapability(Status.DORMANT);
        ((Koopa)target).callSetDisplayChar( newDisplayChar );

        return target + " is now Dormant!";
    }

    @Override
    /**
     * Returns a descriptive statement for the DormantAction
     * @param actor The actor performing the certain action
     * @return a String describing the actor becoming dormant
     */
    public String menuDescription(Actor actor) {
        return target + " becomes Dormant!";
    }

}
