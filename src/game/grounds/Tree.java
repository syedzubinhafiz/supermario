package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.enums.Status;
import game.interfaces.HigherGround;
import game.interfaces.Resettable;

public abstract class Tree extends Ground implements Resettable, HigherGround {
    protected final double SUCCESS_RATE;
    protected final int DAMAGE;
    protected final String NAME;
    protected int turnCounter;
    protected ActionList actions;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar, double success_rate, int damage, String name) {
        super(displayChar);
        this.SUCCESS_RATE = success_rate;
        this.DAMAGE = damage;
        this.NAME = name;
        this.turnCounter = 0;
        Resettable.super.registerInstance();
    }

    public String getName() {
        return this.NAME;
    }


    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET) && (Utils.getRandomBias() <= 0.5)) {
            Dirt d = new Dirt();
            location.setGround(d);
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.INVINCIBLE) && actor.hasCapability(Status.MUST_JUMP)) {
            return true;
        }
        else if (actor.hasCapability(Status.MUST_JUMP)) {
            return false;
        }
        return true;
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        actions = new ActionList();
        actions.add(getMovementAction(actor, location, direction, SUCCESS_RATE, DAMAGE)); // from default interface method
        return actions;
    }



    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
