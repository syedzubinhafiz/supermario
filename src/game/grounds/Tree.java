package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.enums.Status;
import game.interfaces.HigherGround;
import game.interfaces.Resettable;



/**
 * The Tree class represents the Tree object in the game
 *
 * @author: Syed Zubin Hafiz
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.grounds
 */
public abstract class Tree extends Ground implements Resettable, HigherGround {
    /**
     * Protected Attributes
     */

    /**
     * A constant for success_rate of jumps to higher ground
     */
    protected final double SUCCESS_RATE;
    /**
     * A constant for damage when failing to jump to higher ground
     */
    protected final int DAMAGE;
    /**
     * A constant for the name of the tree cycle stage
     */
    protected final String NAME;

    /**
     * A variable called turnCounter which keeps track of turns being played
     */
    protected int turnCounter;
    /**
     * An instance of the ActionList class called 'actions'
     */

    protected ActionList actions;

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
        this.SUCCESS_RATE = success_rate;
        this.DAMAGE = damage;
        this.NAME = name;
        this.turnCounter = 0;
        Resettable.super.registerInstance();
    }

    /**
     * Returns name of the Tree Stage
     *
     * @return
     */
    public String getName() {
        return this.NAME;
    }


    @Override
    /**
     * Overrides the Ground's tick() method to turn tree objects into ground once player decides to reset game
     * @param location Location of the ground
     *
     */
    public void tick(Location location) {
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
        if (actor.hasCapability(Status.MUST_JUMP)) {
            return false;
        }
        return true;
    }


    @Override
    /**
     * Return an instance of ActionList after passing an actor instance,location,direction,success rate and damage
     *
     * @param actor The Actor who might be moving
     * @param location Location of the ground
     * @param direction Direction the actor can jump to
     * @return an instance of the ActionList
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        actions = new ActionList();
        actions.add(getMovementAction(actor, location, direction, SUCCESS_RATE, DAMAGE)); // from default interface method
        return actions;
    }


    @Override
    /**
     * Sets a RESET capability in the enum Status class to invoke the Reset functionality on the Tree class
     */
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
