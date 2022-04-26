package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.enums.Status;
import game.interfaces.HigherGround;
import game.interfaces.Resettable;

public abstract class Tree extends Ground implements Resettable, HigherGround{
    double success_rate;
    int damage;



    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.allowableActions = new ActionList();
        System.out.println("yes");
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
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (actor != location.getActor() && canActorEnter(actor)) {
            this.allowableActions.add(getJumpAction(location));
        }
        return allowableActions;
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            return true;
        }
        else if (actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
            return false;
        }
        return false;
    }


}
