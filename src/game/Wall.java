package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground implements HigherGround {
    private final double SUCCESS_RATE = 0.8;
    private final int DAMAGE = 20;
	private ActionList allowableActions;
	Location location;


    public Wall() {
        super('#');
		this.allowableActions=new ActionList();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
			this.allowableActions.add(getJumpAction());
		}
		return allowableActions;
	}

	@Override
	public JumpAction getJumpAction() {
		return new JumpAction(location, SUCCESS_RATE, DAMAGE);
	}
}

