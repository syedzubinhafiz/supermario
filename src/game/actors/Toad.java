package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Tradeable;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

import java.util.ArrayList;
import java.util.List;

public class Toad extends Actor {

    private static Toad instance;
    private static ArrayList<Tradeable> tradeableInventory;
    private static final String[] sentences = new String[] {"You might need a wrench to smash Koopa's hard shells.",
        "You better get back to finding the Power Stars.", "The Princess is depending on you! You are our only hope.",
        "Being imprisoned in these walls can drive a fungus crazy :("};

    public Toad() {
        super("Toad", 'O', 100); //ask tutor about hitpoints amt for toad
        this.tradeableInventory = new ArrayList<>();
        addItemToTradeableInventory(new PowerStar());
        addItemToTradeableInventory(new SuperMushroom());
        addItemToTradeableInventory(new Wrench());

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

    public static ArrayList<Tradeable> getTradeableInventory() {
        return tradeableInventory;
    }

    public static void removeTradeableItem(Tradeable itemToTrade) {
        tradeableInventory.remove(itemToTrade);
        Tradeable item=null;
        if (itemToTrade instanceof PowerStar) {
            item = new PowerStar();
        }
        else if (itemToTrade instanceof SuperMushroom) {
            item = new SuperMushroom();
        }
        else if (itemToTrade instanceof Wrench) {
            item = new Wrench();
        }
        addItemToTradeableInventory(item);
    }

    public static void addItemToTradeableInventory(Tradeable item) {
        tradeableInventory.add(item);
    }

    public static String getSentence(int index) {
        return sentences[index];
    }

}
