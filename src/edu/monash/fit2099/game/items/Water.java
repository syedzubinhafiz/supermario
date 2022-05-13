package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

public abstract class Water extends Item implements ConsumableItem {


    /***
     * Constructor.
     */
    public Water() {
        super("Water", 'z', false);
    }
}
