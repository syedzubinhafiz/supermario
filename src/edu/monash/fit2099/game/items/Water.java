package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.interfaces.ConsumableItem;


/** Class representing the Water Object in the edu.monash.fit2099.game
 * Provides the most basic functionality of Water object, subclasses to provide further implement specific functionality
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.items
 */
public abstract class Water extends Item implements ConsumableItem {

    /***
     * Constructor.
     */
    public Water(String name, char displayChar) {
        super(name, displayChar, false);
    }

}
