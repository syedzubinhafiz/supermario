package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.MoveMapAction;
import edu.monash.fit2099.game.actors.PiranhaPlant;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.HigherGround;

public class WarpPipe extends Ground implements HigherGround {

//    boolean firstTurn=true;
    boolean hasPiranha=false;
    GameMap mapToPortal;
    Location previousWarpPipe;
    boolean secondMap=false;


    public void setSecondMap(boolean secondMap) {
        this.secondMap = secondMap;
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

    /**
     * Constructor.
     */
    public WarpPipe(GameMap map) {
        super('C');
        this.mapToPortal=map;
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return (!actor.hasCapability(Status.MUST_JUMP));
    }

    @Override
    public String getName() {
        return "Warp Pipe";
    }

    @Override
    public void tick(Location location) {
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
            actions.add(getMovementAction(actor, location, direction, 1, 0)); // from default interface method
        }
        if(location.getActor() == actor) {
            if(previousWarpPipe==null) {
                actions.add(new MoveMapAction(mapToPortal.at(0, 0), " to " + mapToPortal, location, this, " to Lavazone"));
            }
            else {
                actions.add(new MoveMapAction(previousWarpPipe, " to " + previousWarpPipe, location, this, " back to previous pipe"));
            }
        }
        return actions;
    }

//    public void addGameMap(GameMap map){
//        this.mapToPortal=map;
//    }
}