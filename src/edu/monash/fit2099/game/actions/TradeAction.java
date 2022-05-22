package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.actors.Toad;
import edu.monash.fit2099.game.interfaces.Tradeable;


/**
 * Special TradeActopm that allows an actor to trade items with Toad.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class TradeAction extends Action {


    /**
     * The item to be traded
     */
    private final Tradeable itemToTrade;


    /**
     * The value of the trade (the amount it costs)
     */
    private final int tradeValue;


    /**
     * Constructor.
     *
     * @param itemToTrade the item to be traded (Tradeable)
     * @param tradeValue the value of the item to be traded
     */
    public TradeAction(Tradeable itemToTrade, int tradeValue) {
        this.itemToTrade = itemToTrade;
        this.tradeValue = tradeValue;
    }


    /**
     * Executes the TradeAction by reducing the balance from the actor if sufficient and transferring the item to the actor's inventory.
     * If there is not enough balance the trade does not happen.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        boolean reduced = player.getWallet().reduceBalance(tradeValue);
        if (reduced) {
            // remove tradeableItem from Toad's inventory
            Toad.removeTradeableItem(itemToTrade);
            // Add tradeableItem to Player's inventory
            player.addItemToInventory(((Item) itemToTrade));
            return actor + " obtained " + itemToTrade;
        }
        return "You don't have enough coins!";
    }


    /**
     * Returns a descriptive statement for the TradeAction
     * @param actor The actor who might be performing a certain action
     * @return a String describing the trade action for menu use
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + itemToTrade + " ($" + itemToTrade.getValue()+")";
    }

}
