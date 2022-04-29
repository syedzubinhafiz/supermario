package game.interfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DestroyGroundAction;
import game.actions.JumpAction;
import game.enums.Status;
import game.grounds.Dirt;

public interface HigherGround {


    String getName();

    default void addMoveAction(ActionList actions, Actor actor, Location location, String direction, double success_rate, int damage) {
        if(actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            DestroyGroundAction des = new DestroyGroundAction(location, direction);
            actions.add(des);
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.TALL)) {
            JumpAction j= new JumpAction(location, success_rate, damage, direction, getName());
            actions.add(j);
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.TALL)) {
            JumpAction j= new JumpAction(location, 1, 0, direction, getName());
            actions.add(j);
        }
    }

}
