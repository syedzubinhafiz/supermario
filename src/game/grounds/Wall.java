package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.HigherGround;

public class Wall extends Ground implements HigherGround {
    private final double SUCCESS_RATE = 0.8;
    private final int DAMAGE = 20;
    protected ActionList actions;


    public Wall() {
        super('#');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.MUST_JUMP)) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "Wall";
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        actions = new ActionList();
        actions.add(getMovementAction(actor, location, direction, SUCCESS_RATE, DAMAGE)); // from default interface method
        return actions;
    }
}

