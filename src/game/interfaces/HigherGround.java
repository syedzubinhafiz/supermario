package game.interfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;

public interface HigherGround {

    String getName();

    default JumpAction getJumpAction(Location location, double success, int damage, String direction) {
		return new JumpAction(location, success, damage, direction, getName());
    }

    default void addJumpAction(ActionList actions, Actor actor, Location location, String direction, double success_rate, int damage) {
        if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.TALL)
                && !actor.hasCapability(Status.INVINCIBLE)) {
            JumpAction j= getJumpAction(location, success_rate, damage, direction);
            actions.add(j);
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.TALL)
                && !actor.hasCapability(Status.INVINCIBLE)) {
            JumpAction j= getJumpAction(location, 1, 0, direction);
            actions.add(j);
        }
    }

}
