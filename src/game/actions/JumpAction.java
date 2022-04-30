package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;

public class JumpAction extends MoveActorAction {
    private final double SUCCESS_RATE;
    private final int DAMAGE;
    private final String DIRECTION;
    private final String NAME;


    /**
     * JumpAction Constructor initialising all the input parameters
     * @param location
     * @param success_rate
     * @param damage
     * @param direction
     * @param name
     */
    public JumpAction(Location location, double success_rate, int damage, String direction, String name) {
        super(location, direction);
        this.SUCCESS_RATE = success_rate;
        this.DAMAGE = damage;
        this.DIRECTION = direction;
        this.NAME = name;
    }

    @Override
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
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(Utils.getRandomBias() <= this.SUCCESS_RATE){
            map.moveActor(actor, this.moveToLocation); //jump onto the surface
            result = "You have successfully jumped onto the " + this.NAME+"!";
            return result;
        }
        actor.hurt(this.DAMAGE);
        result = "Oh no! You couldn't make the jump and fell down :(. \n" +
                "Damage Received from Fall: " + this.DAMAGE;

        return result;

    }

    @Override
    /**
     * Returns a descriptive statement
     * @oaram actor The actor who might be performing a certain action
     * @return a String describing the exact location coordinates once the actor is on the higher ground
     */
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + this.DIRECTION + " " + this.NAME + " ("+ this.moveToLocation.x() + ", " + this.moveToLocation.y()+")";
    }


}
