package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.ConsumeInventoryItemAction;

/**
 * Interface for Consumable items
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.interfaces
 */
public interface ConsumableItem {

    /**
     * a default interface method for getting the consumeAction of the consumable item
     * @param actor actor consuming the item
     * @return ConsumeAction instance of consume action
     */
    default ConsumeAction getConsumeAction(ConsumableItem this, Actor actor){
        return new ConsumeInventoryItemAction(this);
    }


    /**
     * Method to implemented, responsible for what happens when the consumable item gets consumed by the actor
     * @param actor consuming the item
     */
    void consumedBy(Actor actor);

    /**
     * Method to check if the consumable item can fade before its consumed within the edu.monash.fit2099.game
     * @return true if can fade, else false
     */
    boolean canFade();


}
