package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.Utils;

public class JumpAction extends Action {
private Location location;
private final double SUCCESS_RATE;
private final int DAMAGE;

    public JumpAction(Location location, double SUCCESS_RATE, int DAMAGE) {
        this.location = location;
        this.SUCCESS_RATE = SUCCESS_RATE;
        this.DAMAGE = DAMAGE;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(!actor.hasCapability(Status.TALL)){
            if(Utils.getRandomBias()<=SUCCESS_RATE){
                map.moveActor(actor,location); //jump onto the surface
            }
        else if (actor.hasCapability(Status.TALL)){
                map.moveActor(actor,location);
        }
        else actor.hurt(DAMAGE);
            System.out.println("Oh no! You couldn't make the jump and fell down :( ");
        }
        return "You have successfully jumped onto the higher ground!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + location.getGround();
    }

    @Override
    public String hotkey() {
        return "J";
    }
}
