package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.HigherGround;

/**
 * Class representing a Wall
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.grounds
 */
public class Wall extends Ground implements HigherGround {

    /**
     * success_rate private final attribute representing the rate of success of a jump to wall
     */
    private final double successRate = 0.8;

    /**
     * damage private final attribute representing the damage of a failed jump
     */
    private final int damage = 20;

    /**
     * ActionList to store actions that the Wall can provide to an actor near it.
     */
    protected ActionList actions;

    /**
     * Constructor.
     */
    public Wall() {
        super('#');
    }

    @Override
    /**
     * Checks if the actor can enter the wall
     * @param actor actor who wants to enter wall
     * @return true if can enter, false otherwise
     */
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.MUST_JUMP)) {
            return false;
        }
        return true;
    }

    @Override
    /**
     * Returns the name of the wall
     * @return String the name of the higher ground
     */
    public String getName() {
        return "Wall";
    }

    @Override
    /**
     * Returns true to represent that a wall can block thrown objects.
     */
    public boolean blocksThrownObjects() {
        return true;
    }

    @Override
    /**
     * Method to return a list of actions that the otherActor can perform if it is near Wall.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Ground#allowableActions(Actor, String, GameMap)
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        actions = new ActionList();
        actions.add(getMovementAction(actor, location, direction, successRate, damage)); // from default interface method
        return actions;
    }
}

