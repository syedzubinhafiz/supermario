package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.grounds.Dirt;
import edu.monash.fit2099.game.items.Coin;

import java.util.HashMap;


/**
 * Special DestroyGroundAction that allows an actor to destroy any ground type and turn it into dirt.
 * Moves actor to destroyed ground and drops coin object at the location.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class DestroyGroundAction extends MoveActorAction {


    /**
     * Constructor.
     * @param moveToLocation location to move actor to
     * @param direction direction to move in
     */
    public DestroyGroundAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }


    /**
     * Hashmap Attribute to decide the action hotkey. This is only implemented for DestroyGroundAction because
     * the actor still walks over the higher ground and so it makes sense for it to follow the hotkey from engine code.
     */
    public HashMap<String, String> hotKeyMap = new HashMap<String, String> (){{
        put("North", "8");
        put("South", "2");
        put("East", "6");
        put("West", "4");
        put("North-West", "7");
        put("North-East", "9");
        put("South-West", "1");
        put("South-East", "3");
    }};


    /**
     * Executes the action of destroying ground and transforming it into dirt.
     * Moves actor to location of destroyed ground and drops a coin at the same location.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, this.moveToLocation); //go to surface
        moveToLocation.setGround(new Dirt()); //convertToDirt
        moveToLocation.addItem(new Coin(5)); // drop a coin
        return menuDescription(actor);
    }


    /**
     * Returns a descriptive statement for the DestroyGroundAction
     * @param actor The actor performing the certain action
     * @return a String describing actor destroying the ground
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + direction + " and destroys the ground to Dirt.";
    }


    /**
     * Returns the specific hotkey for the action based on the direction.
     * @return String representing hotkey
     */
    @Override
    public String hotkey() {
        return hotKeyMap.get(direction);
    }

}
