package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.MoveMapAction;
import edu.monash.fit2099.game.actors.PiranhaPlant;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.HigherGround;
import edu.monash.fit2099.game.interfaces.Resettable;


/**
 * A class representing a Warp Pipe map obstacle on the Game Map.
 *
 * @author Chong Jin Yao & Vanessa Khoo
 * @version 3.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public class WarpPipe extends Ground implements HigherGround, Resettable {

    /**
     * Attribute
     * A boolean attribute to flag if there is a Piranha Plant enemy present in the Warp Pipe
     */
    boolean hasPiranha;

    /**
     * Map to portal to
     */
    GameMap mapToPortal;
    /**
     * The location of the previous warp pipe (if the current player came from a different warp pipe)
     */
    Location previousWarpPipe;
    /**
     * Boolean representing if its in the second map
     */
    boolean secondMap;

    /**
     * Attribute
     * a String representing the name of this object
     */
    public final static String NAME = "Warp Pipe";

    /**
     * Boolean constant representing if the WarpPipe can be destroyed or not
     */
    public final static boolean CAN_BE_DESTROYED = false;

    /**
     * Constructor.
     */
    public WarpPipe(GameMap map, boolean secondMap) {
        super('C');
        this.mapToPortal=map;
        this.secondMap=secondMap;
        hasPiranha=false;
        Resettable.super.registerInstance();
    }

    /**
     * Whether or not this WarpPipe belongs to the first map or second map
     * @return true if its in second map
     */
    public boolean isSecondMap() {
        return secondMap;
    }

    /**
     * Set which map to portal to
     * @param mapToPortal the GameMap to portal to
     */
    public void setMapToPortal(GameMap mapToPortal) {
        this.mapToPortal = mapToPortal;
    }

    /**
     * Set the previous location before portaling (previous WarpPipe's location)
     * @param previousWarpPipe location of previous pipe
     */
    public void setPreviousWarpPipe(Location previousWarpPipe) {
        this.previousWarpPipe = previousWarpPipe;
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
        return (!actor.hasCapability(Status.MUST_JUMP));
    }

    @Override
    /**
     * Returns the name, Implements the Speakable interface.
     * @return String the name of the higher ground
     */
    public String getName() {
        return NAME;
    }

    @Override
    /**
     * Overrides the Ground's tick() method
     * @param location Location of the ground
     *
     */
    public void tick(Location location) {
        if(this.hasCapability(Status.RESET)) {
            hasPiranha=false;
        }
        if (location.getActor() == null && !hasPiranha) {
            //spawn piranha on top of it
            location.addActor(new PiranhaPlant());
            hasPiranha=true;
        }
        super.tick(location);
    }

    @Override
    /**
     * Method to return a list of actions that the otherActor can perform if it is near WarpPipe.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Ground#allowableActions(Actor, String, GameMap)
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if( location.getActor() == null ){
            actions.add(getFinalMovementAction(actor, location, direction)); // from default interface method
        }
        if(location.getActor() == actor) {
            if(previousWarpPipe==null) {
                actions.add(new MoveMapAction(mapToPortal.at(0, 0), " to " + mapToPortal, location, this, " to Lava Zone"));
            }
            else {
                actions.add(new MoveMapAction(previousWarpPipe, " to " + previousWarpPipe, location, this, " back to previous pipe"));
            }
        }
        return actions;
    }

    @Override
    /**
     * Method to get the final movement action. Implements the method from HigherGround interface.
     * @return MoveActorAction action
     */
    public MoveActorAction getFinalMovementAction(Actor actor, Location location, String direction) {
        return getMovementAction(actor, location, direction, 1, 0, CAN_BE_DESTROYED);
    }
    @Override
    /**
     * Implements the resetInstance() method in Resettable interface.
     * Sets a RESET capability in the enum Status class to invoke the Reset functionality
     * on the WarpPipe class.
     * @see Resettable#resetInstance()
     */
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }


}
