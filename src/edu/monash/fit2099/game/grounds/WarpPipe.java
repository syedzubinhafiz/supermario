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

public class WarpPipe extends Ground implements HigherGround, Resettable {

    boolean hasPiranha;
    GameMap mapToPortal;
    Location previousWarpPipe;
    boolean secondMap;
    public final static String NAME = "Warp Pipe";
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

    public boolean isSecondMap() {
        return secondMap;
    }

    public void setMapToPortal(GameMap mapToPortal) {
        this.mapToPortal = mapToPortal;
    }

    public void setPreviousWarpPipe(Location previousWarpPipe) {
        this.previousWarpPipe = previousWarpPipe;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return (!actor.hasCapability(Status.MUST_JUMP));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
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
    public MoveActorAction getFinalMovementAction(Actor actor, Location location, String direction) {
        return getMovementAction(actor, location, direction, 1, 0, CAN_BE_DESTROYED);
    }
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }


}
