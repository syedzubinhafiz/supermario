package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;


/**
 * Extension of ConsumeAction class that allows Players to consume items stored in their inventory.
 *
 * @author Chong Jin Yao & Team1
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class ConsumeInventoryItemAction extends ConsumeAction {


    /**
     * Constructor
     * @param item the ConsumableItem object to be consumed.
     */
    public ConsumeInventoryItemAction ( ConsumableItem item ) {
        super(item);
    }


    /**
     * Executes base ConsumeAction class with super() to make use of consumedBy() to give player effects of
     * consuming item in inventory.
     * Removes item assigned to attribute in parent class from player inventory.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to let the Player know that he has successfully consumed the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        map.locationOf(actor).removeItem((Item) consumableItem);
        return actor +" consumed " + consumableItem+".";
    }

}
