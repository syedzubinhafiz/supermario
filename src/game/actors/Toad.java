package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TalkWithToadAction;
import game.interfaces.Tradeable;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;
import java.util.ArrayList;


public class Toad extends Actor {

    private static Toad instance;
    private static ArrayList<Tradeable> tradeableInventory;

    private Toad() {
        super("Toad", 'O', 100);
        this.tradeableInventory = new ArrayList<>();
        tradeableInventory.add(new PowerStar());
        tradeableInventory.add(new SuperMushroom());
        tradeableInventory.add(new Wrench());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public static Toad getInstance() {
        if (instance == null) {
            instance = new Toad();
        }
        return instance;
    }

    public static ArrayList<Tradeable> getTradeableInventory() {
        return tradeableInventory;
    }

    public static void removeTradeableItem(Tradeable itemToTrade) {
        Tradeable newItem = itemToTrade.newInstance();
        tradeableInventory.remove(itemToTrade);
        tradeableInventory.add(newItem);
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions=new ActionList();
        for (Tradeable item : getTradeableInventory()) {
            actions.add(item.getTradeAction());
        }
        actions.add(new TalkWithToadAction());
        return actions;
    }
}
