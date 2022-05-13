package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.DrinkFromBottleAction;
import edu.monash.fit2099.game.actions.ObtainedAction;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Obtainable;

import java.util.ArrayList;
import java.util.List;

public class Bottle extends Item implements Obtainable {

    ArrayList<Water> waterArrayList = new ArrayList<Water>();

    /***
     * Constructor.

     */
    public Bottle() {
        super("Bottle", 'b', false);
    }

    public void addWater(Water water) {
        waterArrayList.add(water);
    }

    public void removeWater() {
        waterArrayList.remove(0);
    }

    public boolean isEmpty() { return waterArrayList.size() == 0; }
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(!isEmpty()) {
            actions.add(new DrinkFromBottleAction(waterArrayList.get(0), this));
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
}
