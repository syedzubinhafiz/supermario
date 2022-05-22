package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.grounds.WarpPipe;


/**
 * Special Action class tasked with moving the Player entity to another map
 *
 * @author Chong Jin Yao & Team1
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class MoveMapAction extends MoveActorAction {

    /**
     * Attribute
     * Current location of actor
     */
    Location current;


    /**
     * Attribute
     * WarpPipe object that allows Player to teleport to the new map
     */
    WarpPipe previousWp;

    /**
     * Attribute
     * a String to describe the location the actor( Player ) is travelling to
     */
    String description;


    /**
     * Constructor
     * @param moveToLocation The location to move the actor to.
     * @param direction The direction of movement.
     * @param currentLocation The current location of the actor.
     * @param wp The warp pipe object the player is using to teleport.
     * @param description String describing the location the player is travelling to.
     */
    public MoveMapAction(Location moveToLocation, String direction, Location currentLocation, WarpPipe wp, String description) {
        super(moveToLocation, direction);
        this.current = currentLocation;
        this.previousWp = wp;
        this.description=description;
    }


    /**
     * Executes the MoveMapAction by sending the player to new map.
     * Saves the location of the Warp Pipe on the original map to send the player back
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to show the message output after the execution of the action
     */
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
