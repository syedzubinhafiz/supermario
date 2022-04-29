package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;
import game.interfaces.HigherGround;

public class Wall extends Ground implements HigherGround {
    private final double SUCCESS_RATE = 0.8;
    private final int DAMAGE = 20;


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
    	ActionList actions = new ActionList();

		if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
			JumpAction j= getJumpAction(location, SUCCESS_RATE, DAMAGE, direction);
			actions.add(j);
		}
		else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
			JumpAction j= getJumpAction(location, 1, 0, direction);
			actions.add(j);
		}
		return actions;
	}


	@Override
	public JumpAction getJumpAction(Location location, double success, int damage, String direction) {
		return new JumpAction(location, success, damage, direction, getName());
	}


}

