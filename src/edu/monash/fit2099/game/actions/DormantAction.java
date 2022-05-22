package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Koopa;
import edu.monash.fit2099.game.enums.Status;


/**
 * Special Action that allows an actor to become dormant ( currently only used on Koopas ).
 *
 * @author Vanessa Khoo Ming Yi
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class DormantAction extends Action {


    /**
     * Attribute
     * Target actor that is to be made dormant
     */
    protected Actor target;


    /**
     * New display character for dormant actor
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


    /**
     * Executes the dormant action, where it adds the dormant capability status to actor and changes it's display character.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.addCapability(Status.DORMANT);
        ((Koopa)target).callSetDisplayChar( newDisplayChar );

        return target + " is now Dormant!";
    }


    /**
     * Returns a descriptive statement for the DormantAction to let the player know that the Koopa has gone dormant.
     * @param actor The actor performing the action
     * @return a String describing the actor becoming dormant
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " becomes Dormant!";
    }

}
