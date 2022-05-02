package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TalkWithToadAction;
import game.interfaces.Tradeable;
import java.util.ArrayList;

/**
 * A global Singleton class representing the Toad.
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.actors
 */
public class Toad extends Actor {

    /**
     * Singleton Toad instance
     */
    private static Toad instance;

    /**
     * An arraylist of Tradeable items
     */
    private static ArrayList<Tradeable> tradeableInventory;

    /**
     * Constructor.
     */
    private Toad() {
        super("Toad", 'O', 100);
        this.tradeableInventory = new ArrayList<>();
    }

    @Override
    /**
     * Method to return the action that needs to be done for the current turn of the Toad.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be played
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Get the singleton instance of Toad
     * @return Toad singleton instance
     */
    public static Toad getInstance() {
        if (instance == null) {
            instance = new Toad();
        }
        return instance;
    }

    /**
     * Getter for the tradeableInventory attribute
     * @return ArrayList of Tradeable items.
     */
    public static ArrayList<Tradeable> getTradeableInventory() {
        return tradeableInventory;
    }

    /**
     * Method to remove the tradeable item from the inventory and also replenish the inventory of Toad
     * @param itemToTrade
     */
    public static void removeTradeableItem(Tradeable itemToTrade) {
        Tradeable newItem = itemToTrade.newInstance();
        tradeableInventory.remove(itemToTrade);
        tradeableInventory.add(newItem);
    }


    @Override
    /**
     * Method to return a list of actions that the otherActor can perform if it is near Toad.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions=new ActionList();
        for (Tradeable item : getTradeableInventory()) {
            actions.add(item.getTradeAction());
        }
        actions.add(new TalkWithToadAction());
        return actions;
    }
}
