package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.enums.Status;
import game.interfaces.HigherGround;
import game.interfaces.Resettable;

public abstract class Tree extends Ground implements Resettable, HigherGround{
    double success_rate;
    int damage;
    boolean jumpActionProvided;
    JumpAction lastAddedAction;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.allowableActions = new ActionList();
        this.jumpActionProvided=false;
        Resettable.super.registerInstance();
    }

    int turnCounter;
    ActionList allowableActions;

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET) && (Utils.getRandomBias() <= 0.5)) {
            Dirt d = new Dirt();
            location.setGround(d);
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.MUST_JUMP)) {
            return false;
        }
        return true;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if(this.jumpActionProvided==true) {
            this.allowableActions.remove(lastAddedAction);
        }
        if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
            JumpAction j= getJumpAction(location, success_rate, damage, direction);
            this.allowableActions.add(j);
            this.jumpActionProvided=true;
            lastAddedAction=j;
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            JumpAction j= getJumpAction(location, 1, 0, direction);
            this.allowableActions.add(j);
            this.jumpActionProvided=true;
            lastAddedAction=j;
        }
        return allowableActions;
    }


}
