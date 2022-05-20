package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.actions.DrinkFromBottleAction;
import edu.monash.fit2099.game.actions.ObtainedAction;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.Obtainable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements Obtainable {

    Stack<Water> waterStack = new Stack<Water>();
    private static Bottle instance;

    /***
     * Constructor.

     */
    private Bottle() {
        super("Bottle", 'b', false);
    }

    public void addWater(Water water) {
        waterStack.push(water);
    }

    public ConsumableItem removeWater() {
        Water water=null;
        if(!waterStack.isEmpty()) {
            water=waterStack.pop();
        }
        return water;
    }

    public boolean isEmpty() { return waterStack.isEmpty(); }

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

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(!isEmpty()) {
            actions.add(new DrinkFromBottleAction(waterStack.peek(), this));
        }
        return actions;
    }

    @Override
    public ObtainedAction getObtainedAction() {
        return new ObtainedAction(this);
    }

    @Override
    public void obtainedBy(Actor actor) {
        actor.addCapability(Status.HAS_BOTTLE);
        actor.addItemToInventory(this);
        Player player = (Player) actor;
        player.setBottle(this);
    }

    public static Bottle getInstance() {
        if (instance == null) {
            instance = new Bottle();
        }
        return instance;
    }

}
