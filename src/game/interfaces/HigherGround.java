package game.interfaces;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DestroyGroundAction;
import game.actions.JumpAction;
import game.enums.Status;

public interface HigherGround {


    String getName();

    default MoveActorAction getMovementAction(Actor actor, Location location, String direction, double success_rate, int damage) {
        MoveActorAction action = null;
        if(actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            DestroyGroundAction des = new DestroyGroundAction(location, direction);
            action = des;
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.TALL)) {
            JumpAction j= new JumpAction(location, success_rate, damage, direction, getName());
            action = j;
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.TALL)) {
            JumpAction j= new JumpAction(location, 1, 0, direction, getName());
            action = j;
        }
        return action;
    }

}
