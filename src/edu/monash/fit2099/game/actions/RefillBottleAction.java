package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.items.Bottle;
import edu.monash.fit2099.game.items.Water;


/**
 * Special action class tasked with refilling the Players bottle with Water objects from MagicalFountains
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.engine.actions
 */
public class RefillBottleAction extends Action {


    /**
     * Attribute
     * Water object to fill the bottle with.
     */
    private Water water;


    /**
     * Attribute
     * Fountain instance that provides the water.
     */
    private MagicalFountain fountain;


    /**
     * Constructor
     * @param water Type of water to fill the bottle with.
     * @param fountain The fountain from which water is to be taken from.
     */
    public RefillBottleAction(Water water, MagicalFountain fountain){
        this.water = water;
        this.fountain = fountain;
    }


    /**
     * Executes the RefillBottleAction by getting the instance of the singleton Bottle class from the players inventory and
     * using its in-built methods to add the water.
     * Removes Water object from the fountain after doing so.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to let the player know they have refilled their bottle successfully
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // refills the bottle of player
        Bottle.getInstance().addWater(water);
        fountain.removeWater(); // remove the water from fountain
        return "Mario refills bottle from " + fountain.getName() + " (" + fountain.getCapacity()+ "/"+fountain.getMaxCapacity()+")";
    }


    /**
     * Returns a descriptive statement for RefillBottleAction
     * @param actor The actor performing the action.
     * @return a String describing the action to carry out for menu use.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Refill " + actor + "\'s bottle.";
    }
}
