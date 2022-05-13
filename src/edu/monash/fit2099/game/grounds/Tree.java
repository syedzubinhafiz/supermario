package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.Utils;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.HigherGround;
import edu.monash.fit2099.game.interfaces.Resettable;

/**
 * An abstract class representing a Tree in the edu.monash.fit2099.game
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public abstract class Tree extends Ground implements Resettable, HigherGround {
    /**
     * success_rate of a MUST_JUMP actor jumping onto Tree
     */
    protected final double success_rate;
    /**
     * damage to actor if jump fails
     */
    protected final int damage;
    /**
     * name of the tree instance
     */
    protected final String name;
    /**
     * counter for the number of turns the instance has went through
     */
    protected int turnCounter;
    /**
     * list of actions the Tree provides to actors near to it
     */
    protected ActionList actions;

    public final static boolean CAN_BE_DESTROYED=true;

    /**
     * Constructor.
     *
     * @param displayChar  character to display for this type of terrain
     * @param success_rate the success rate for completion of jump onto higher ground
     * @param damage       the damage if player fails to jump onto higher ground
     * @param name         name of the stage the Tree object is in
     */
    public Tree(char displayChar, double success_rate, int damage, String name) {
        super(displayChar);
        this.success_rate = success_rate;
        this.damage = damage;
        this.name = name;
        this.turnCounter = 0;
        Resettable.super.registerInstance();
        this.addCapability(Status.HIGHER_GROUND);
    }

    /**
     * Returns name of the Tree Stage
     *
     * @return String name of the Tree stage
     */
    public String getName() {
        return this.name;
    }


    @Override
    /**
     * Overrides the Ground's tick() method to turn tree objects into ground once player decides to reset edu.monash.fit2099.game
     * @param location Location of the ground
     *
     */
    public void tick(Location location) {
        turnCounter++;
        if (this.hasCapability(Status.RESET) && (Utils.getRandomBias() <= 0.5)) {
            Dirt d = new Dirt();
            location.setGround(d);
        }
    }

    @Override
    /**
     * Returns true if an Actor can enter this location.
     *
     * If only character possesses a power star actor can enter the location,i.e walk over higher ground
     * Returns false if Actor does not have a special ability and must perform jump action to enter
     * @param actor the Actor who might be moving
     * @return true if an Actor can enter this location.
     *
     */
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.MUST_JUMP);
    }


    @Override
    /**
     * Return an instance of ActionList after passing an actor instance,location,direction,success rate and damage
     *
     * @param actor The Actor who might be moving
     * @param location Location of the ground
     * @param direction Direction the actor can jump to
     * @return an instance of the ActionList
     * @see Ground#allowableActions(Actor, Location, String) 
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        actions = new ActionList();
        actions.add(getFinalMovementAction(actor, location, direction)); // from default interface method
        return actions;
    }


    @Override
    public MoveActorAction getFinalMovementAction(Actor actor, Location location, String direction) {
        return getMovementAction(actor, location, direction, success_rate, damage, CAN_BE_DESTROYED);
    }

    @Override
    /**
     * Implements the resetInstance() method in Resettable interface.
     * Sets a RESET capability in the enum Status class to invoke the Reset functionality
     * on the Tree class.
     * @see Resettable#resetInstance()
     */
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
