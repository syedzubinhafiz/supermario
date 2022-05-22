package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.FadeableItem;

/**
 * Special PickUpItemAction that allows an actor to consume consumable items.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public abstract class ConsumeAction extends Action {

    //attributes
    /**
     * The consumableItem to be consumed
     */
    protected final ConsumableItem consumableItem;


    /**
     * Constructor
     * @param item the item to consume
     */
    public ConsumeAction (ConsumableItem item) {
        this.consumableItem = item;

    }


    /**
     * Executes the consume action by making the consumable item consumed by actor and removing the consumed item
     * from the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see PickUpItemAction#execute(Actor actor, GameMap map)
     */
    //Could remove method entirely
    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.consumedBy( actor );
        return consumableItem + " is consumed.";

    }

    /**
     * Returns a descriptive statement for the ConsumeAction
     * @param actor The actor performing the certain action
     * @return a String describing actor consuming the item
     */
    //Could remove method entirely
    @Override
    public String menuDescription(Actor actor) {
        String result= actor + " consumes "+ consumableItem ;
        if (consumableItem.canFade()) {
            result = actor + " consumes "+ consumableItem + " - " + ((FadeableItem) consumableItem).getFadingTimeOnFloorInventory() + " turns remaining";
        }
        return result;
    }
}
