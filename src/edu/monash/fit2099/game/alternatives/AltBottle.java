package edu.monash.fit2099.game.alternatives;

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
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;
import java.util.List;

public class AltBottle extends Item implements Obtainable {

    ArrayList<Water> waterArrayList = new ArrayList<Water>();
    private static AltBottle instance;

    private AltBottle() {
        super("Bottle", 'b', false);
    }

    public void addWater(Water water) {
        waterArrayList.add(water);
    }

    public void removeWater() {
        waterArrayList.remove(-1);
    }

    public boolean isEmpty() { return waterArrayList.size() == 0; }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();

        if(!isEmpty()) {
            actions.add(new AltConsumeAction( waterArrayList.get(-1) ));
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

    public static edu.monash.fit2099.game.items.Bottle getInstance() {
        if (instance == null) {
            instance = new edu.monash.fit2099.game.items.Bottle();
        }

        return instance;
    }

}

