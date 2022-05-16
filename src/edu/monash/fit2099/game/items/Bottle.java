package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.actions.itemrelated.DrinkFromBottleAction;
import edu.monash.fit2099.game.actions.itemrelated.ObtainedAction;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Obtainable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements Obtainable {

//    ArrayList<Water> waterArrayList = new ArrayList<Water>();
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

    public void removeWater() {
        waterStack.pop();
    }

    public boolean isEmpty() { return waterStack.isEmpty(); }

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
