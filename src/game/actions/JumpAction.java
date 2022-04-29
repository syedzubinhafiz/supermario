package game.actions;

import edu.monash.fit2099.engine.actions.Action;
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


    public JumpAction(Location location, double success_rate, int damage, String direction, String name) {
        super(location, direction);
        this.SUCCESS_RATE = success_rate;
        this.DAMAGE = damage;
        this.DIRECTION = direction;
        this.NAME = name;
    }

    @Override
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
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + this.DIRECTION + " " + this.NAME + " ("+ this.moveToLocation.x() + ", " + this.moveToLocation.y()+")";
    }


}
