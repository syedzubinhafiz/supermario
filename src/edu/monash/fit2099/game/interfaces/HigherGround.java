package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.DestroyGroundAction;
import edu.monash.fit2099.game.actions.JumpAction;
import edu.monash.fit2099.game.enums.Status;

/**
 * This interface handles the interaction player has with higher grounds in the map of the edu.monash.fit2099.game
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.interfaces
 */
public interface HigherGround {

    /**
     *
     * @return String to get name of the Tree Cycle Stage
     */
    String getName();

    MoveActorAction getFinalMovementAction(Actor actor, Location location, String direction);
    /**
     *This method checks if the actor possesses any magical items and accounts for their effects on its jumping capabilities
     * while also adding the jump option to the allowableActionsList
     * @param actor Actor who might be performing a certain action
     * @param location Location of the ground
     * @param direction actor needs to jump on the higher ground at a certain direction
     * @param success_rate of making jump to a particular higher ground
     * @param damage Damage dealt when jump to higher ground fails
     * @return An instance of MoveActorAction
     */
    default MoveActorAction getMovementAction(Actor actor, Location location, String direction, double success_rate, int damage, boolean canBeDestroyed) {
        MoveActorAction action = null;

        // Since only interface needed to change, no need to change both
        // tree and wall to update.
        if(actor != location.getActor() && actor.hasCapability(Status.FLYING)) {
            MoveActorAction move = new MoveActorAction(location, direction);
            action = move;
        }

        if(actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP)) {
            if (actor.hasCapability(Status.INVINCIBLE)) {
                if(canBeDestroyed) {
                    DestroyGroundAction des = new DestroyGroundAction(location, direction);
                    action=des;
                }
                else {
                    JumpAction j = new JumpAction(location, 1, 0, direction, getName());
                    action = j;
                }
            } else if (!actor.hasCapability(Status.TALL)) {
                JumpAction j = new JumpAction(location, success_rate, damage, direction, getName());
                action = j;
            } else if (actor.hasCapability(Status.TALL)) {
                JumpAction j = new JumpAction(location, 1, 0, direction, getName());
                action = j;
            }
        }
        return action;
    }

}
