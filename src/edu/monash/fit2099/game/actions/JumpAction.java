package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.Utils;


/**
 * This class handles the 'jump' functionality of the actor in the edu.monash.fit2099.game
 *
 * @author Syed Zubin Hafiz
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class JumpAction extends MoveActorAction {
    /**
     * Private Attributes
     */


    /**
     * A attribute for success_rate which represents the probability of completion of jump
     */
    private final double successRate;


    /**
     * A attribute for damage dealt when actor fails to complete the jump
     */
    private final int damage;


    /**
     * A attribute for the name of the higher ground
     */
    private final String name;


    /**
     * JumpAction Constructor initialising all the input parameters
     * @param location location to jump to
     * @param success_rate success_rate of the jump
     * @param damage damage of the jump if it fails
     * @param direction direction of the jump
     * @param name name of the higher ground
     */
    public JumpAction(Location location, double success_rate, int damage, String direction, String name) {
        super(location, direction);
        this.successRate = success_rate;
        this.damage = damage;
        this.name = name;
    }


    /**
     * This method overrides the execute() method which returns a completion statement if the randomBias attained
     * agrees with the probability resulting in the actor jumping on to the higher ground.
     * Otherwise, the actor is dealt damage and remains in the same location he was in. A failure statement is returned
     * instead.
     *
     *
     * @param actor The Actor who might be moving
     * @param map The map in which the actors move
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(Utils.getRandomBias() <= this.successRate){
            map.moveActor(actor, this.moveToLocation); //jump onto the surface
            result = actor +" has successfully jumped onto the " + this.name+ " at " + direction +"!";
        }
        else {
            actor.hurt(this.damage);
            result = "Oh no! " + actor + " couldn't make the jump and fell down :(. Damage Received from Fall: " + this.damage;

        }
        return result;

    }


    /**
     * Returns a descriptive statement
     * @param actor The actor who might be performing a certain action
     * @return a String describing the exact location coordinates once the actor is on the higher ground
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + this.direction + " " + this.name + " ("+ this.moveToLocation.x() + ", " + this.moveToLocation.y()+")";
    }

}

