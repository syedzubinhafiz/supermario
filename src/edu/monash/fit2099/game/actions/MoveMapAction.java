package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.grounds.WarpPipe;

public class MoveMapAction extends MoveActorAction {

    Location current;
    WarpPipe wp;
    String description;

    public MoveMapAction(Location moveToLocation, String direction, Location currentLocation, WarpPipe wp, String description) {
        super(moveToLocation, direction);
        this.current = currentLocation;
        this.wp=wp;
        this.description=description;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //move actor
        if(map.isAnActorAt(moveToLocation)) {
            // instantly kill actor (piranha) at the warp pipe
            map.removeActor(moveToLocation.getActor());
            map.moveActor(actor, moveToLocation);
        }
        else {
            map.moveActor(actor, moveToLocation);
        }
        //set warpPipe's previous location as where it went to for warppipe's future
//        wp.setPreviousWarpPipe(moveToLocation);

        //set destination warppipe's location to be
        if(!wp.isSecondMap()) {
            WarpPipe dest = (WarpPipe) moveToLocation.getGround();
            dest.setPreviousWarpPipe(current);
        }

        return "moved";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport" +description;
    }
}
