package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.grounds.WarpPipe;

public class MoveMapAction extends MoveActorAction {

    Location current;
    WarpPipe previousWp;
    String description;

    public MoveMapAction(Location moveToLocation, String direction, Location currentLocation, WarpPipe wp, String description) {
        super(moveToLocation, direction);
        this.current = currentLocation;
        this.previousWp = wp;
        this.description=description;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.isAnActorAt(moveToLocation)) {
            // instantly kill actor (piranha) at the warp pipe
            map.removeActor(moveToLocation.getActor());
        }
        super.execute(actor, map); //move actor

        // set the previous warp pipe location of destination warpPipe as the current location (before moving)
        if(!previousWp.isSecondMap()) {
            WarpPipe dest = (WarpPipe) moveToLocation.getGround();
            dest.setPreviousWarpPipe(current);
        }
        String message = actor + " has moved to " + map;
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport" +description;
    }
}
