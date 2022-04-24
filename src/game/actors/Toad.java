package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Tradeable;

import java.util.ArrayList;

public class Toad extends Actor {

    private static Toad instance;
    public ArrayList<Tradeable> tradeableInventory;

    public Toad(String name, char displayChar, int hitPoints) {
        super("Toad", 'O', hitPoints);
        this.tradeableInventory = new ArrayList<>();
        PowerStar ps = new PowerStar();
        SuperMushroom sm = new SuperMushroom();
        Wrench w = new Wrench();
        this.tradeableInventory.add(ps);
        this.tradeableInventory.add(sm);
        this.tradeableInventory.add(w);

        getInstance(); //double check with bootcamp
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    public static Toad getInstance() {
        if (instance == null) {
            instance = new Toad();
        }
        return instance;
    }

    public ArrayList<Tradeable> getTradeableInventory() {
        return tradeableInventory;
    }

    public static void purchaseItem(Tradeable itemToTrade) {
        // REMEMBER TO USE THIS in TradeAction!! (add it in)
        tradeableInventory.remove(itemToTrade);
        // replenish inventory - (think of better way)
        if (itemToTrade instanceof PowerStar) {
            tradeableInventory.add(new PowerStar());
        }
        else if (itemToTrade instanceof SuperMushroom) {
            tradeableInventory.add(new SuperMushroom());
        }
        else if (itemToTrade instanceof Wrench) {
            tradeableInventory.add(new Wrench());
        }
    }

}
