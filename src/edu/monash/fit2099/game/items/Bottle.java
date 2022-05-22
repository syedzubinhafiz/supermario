package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.actions.DrinkFromBottleAction;
import edu.monash.fit2099.game.actions.ObtainedAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.Obtainable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * A global Singleton class representing a Bottle object the player can obtain in the game.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.items
 */
public class Bottle extends Item implements Obtainable {


    /**
     * Attribute
     * A stack storing all Water objects the Player obtains from Fountains.
     */
    Stack<Water> waterStack = new Stack<Water>();


    /**
     * Singleton Bottle instance
     */
    private static Bottle instance;


    /***
     * Singleton Private Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
    }


    /**
     * Method to aid in the manipulation of the waterStack attribute.
     * Responsible for adding water to the Stack.
     * @param water Water object to be added
     */
    public void addWater(Water water) {
        waterStack.push(water);
    }


    /**
     * Method to aid in the manipulation of the waterStack attribute.
     * Responsible for removing Water objects from the Stack and assigning them to a variable to be returned.
     * @return a variable 'water', which is an instance of ConsumableItem.
     */
    public ConsumableItem removeWater() {
        Water water=null;
        if(!waterStack.isEmpty()) {
            water=waterStack.pop();
        }
        return water;
    }


    /**
     * Method that returns True or False depending on whether the waterStack attribute is empty or not.
     * @return True if empty, False if not.
     */
    public boolean isEmpty() { return waterStack.isEmpty(); }


    /**
     * Method that converts all elements of the Stack into String to display on menu or wherever else needed.
     * @return a String listing all Water objects present in the Stack.
     */
    public String getItems() {
        // Get the slice of the Array
        String slice = "[";
        Object[] waters = waterStack.toArray();

        // Copy elements of arr to slice
        if(waters != null) {
            if (waters.length > 1) {
                for (int i = 0; i < 2; i++) {
                    slice += waters[i]+ ", ";
                }
            } else {
                slice += waters[0]+", ";
            }

        }
        // return the slice
        slice = slice.substring(0, slice.length()-2) + "]";
        return slice;
    }


    /**
     * Method to return a list of Actions that an Actor in possession of the Singleton Bottle object can perform.
     * @return list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(!isEmpty()) {
            actions.add(new DrinkFromBottleAction(waterStack.peek()));
        }
        return actions;
    }


    /**
     * Method tasked with returning ObtainedAction for the Singleton Bottle Object if needed.
     * Implemented from Obtainable Interface.
     * @return Instance of ObtainedAction with Bottle object as parameter.
     */
    @Override
    public ObtainedAction getObtainedAction() {
        return new ObtainedAction(this);
    }

    /**
     * Method performs any necessary changes to Actor when bottle is obtained by given Actor.
     * @param actor Actor obtaining the item
     */
    @Override
    public void obtainedBy(Actor actor) {
        actor.addCapability(Status.HAS_BOTTLE);

    }


    /**
     * Method responsible for providing access to the Singleton instance of Bottle.
     * @return Bottle singleton instance 'instance'.
     */
    public static Bottle getInstance() {
        if (instance == null) {
            instance = new Bottle();
        }
        return instance;
    }

}

