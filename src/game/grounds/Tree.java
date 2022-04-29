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

public abstract class Tree extends Ground implements Resettable, HigherGround {
    protected double success_rate;
    protected int damage;
    protected int turnCounter;
    protected String name;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar, double success_rate, int damage, String name) {
        super(displayChar);
        this.success_rate=success_rate;
        this.damage=damage;
        this.turnCounter=0;
        this.name = name;
        Resettable.super.registerInstance();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public JumpAction getJumpAction(Location location, double success, int damage, String direction) {
        return new JumpAction(location, success, damage, direction, getName());
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
        ActionList actions = new ActionList();
        if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.TALL)) {
            JumpAction j= getJumpAction(location, success_rate, damage, direction);
            actions.add(j);
        }
        else if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.TALL)) {
            JumpAction j= getJumpAction(location, 1, 0, direction);
            actions.add(j);
        }
        return actions;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
