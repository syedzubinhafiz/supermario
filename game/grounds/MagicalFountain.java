package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.RefillBottleAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;


/**
 * MagicalFountain class is an abstract class that houses the basic functionalities of Fountain objects in the game.
 * It tells the game how to store the water, what you can do with the water and how to refill itself but not what water it can store.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public abstract class MagicalFountain extends Ground {


    /**
     * Attribute
     * an ArrayList storing Water objects in the fountain
     */
    protected ArrayList<Water> fountainWater;


    /**
     * Attribute
     * a final integer to represent the maximum capacity of the fountain
     */
    protected final int FINAL_WATER_CAPACITY=10;

    /**
     * Attribute
     * a String storing the name of the fountain
     */
    protected String name;


    /**
     * Attribute
     * an Integer for counting the turns until the fountain needs to be refilled when empty
     */
    private int rechargeTimer = 0;

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     * @param name a String representing the fountain type
     */
    public MagicalFountain(char displayChar, String name) {
        super(displayChar);
        this.name=name;
        this.addCapability(Status.FOUNTAIN);
    }


    /**
     * A tick method that informs the fountain of the passage of time.
     * Used in this case to check if the fountain is empty and needs refilling.
     * @param location The location of the Ground
     */
    //Implementation for Refilling water
    @Override
    public void tick(Location location) {
        if(fountainWater.isEmpty()) {
            // replenish in next 5 turns once empty
            rechargeTimer++;
            if(rechargeTimer==6) {
                // replenish
                this.fillFountain();
                //to reset for the next time the fountain goes empty
                rechargeTimer = 0;
            }
        }
    }


    /**
     * Method to return a list of actions that the Actor can perform if it is near a Magical Fountain.
     * @param actor the Actor that might perform an action.
     * @param location  String representing the direction of the other Actor
     * @param direction      current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions=new ActionList();
        if(actor.hasCapability(Status.HAS_BOTTLE) && !fountainWater.isEmpty() && location.getActor()==actor){
            actions.add(new RefillBottleAction(getWater(), this));
        }
        return actions;
    }

    /**
     * Getter for Water object stored in the Fountain.
     * @return instance of Water object
     */
    public Water getWater() {
        if(!fountainWater.isEmpty()) {
            return fountainWater.get(0);
        }
        return null;
    }

    /**
     * Getter for ConsumableItem object stored in Fountain.
     * @return instance of ConsumableItem object
     */
    public ConsumableItem removeWater(){
        ConsumableItem water = getWater();
        if(water!=null) {
            fountainWater.remove(0);
        }
        return water;
    }


    /**
     * Mandatory method for filling of the fountain, to be implemented in subclasses
     */
    //New method to fill fountain
    public abstract void fillFountain();


    /**
     * Getter for ArrayList attribute size.
     * @return Integer representing number of elements in fountainWater attribute
     */
    public int getCapacity() { return fountainWater.size();}

    /**
     * Getter for FINAL_WATER_CAPACITY attribute.
     * @return Integer representing maximum storage value of fountain.
     */
    public int getMaxCapacity() { return FINAL_WATER_CAPACITY;}


    /**
     * Getter for name attribute
     * @return a String representing name of the fountain.
     */
    public String getName() {return this.name;}

}
