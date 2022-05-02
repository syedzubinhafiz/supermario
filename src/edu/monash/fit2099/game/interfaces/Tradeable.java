package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.game.actions.TradeAction;

/**
 * Interface for Tradeable items
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.interfaces
 */
public interface Tradeable {

    /**
     * Method to return the specific tradeAction for the Tradeable item
     * @return TradeAction action of trading the tradeable item
     */
    TradeAction getTradeAction();

    /**
     * Gets the value of the tradeable item
     * @return int value of the item
     */
    int getValue();

    /**
     * Method to return a new instance of the class of the Tradeable item.
     * This is so that the seller can replenish the items without depending on indiividual classes.
     * @return Tradeable new item of the class of the item that was traded.
     */
    Tradeable newInstance();
}
