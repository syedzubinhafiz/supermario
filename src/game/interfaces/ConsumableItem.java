package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;

/**
 * Interface for Consumable items
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.interfaces
 */
public interface ConsumableItem {

    /**
     * a default interface method for getting the consumeAction of the consumable item
     * @param actor
     * @return ConsumeAction instance of consume action
     */
    default ConsumeAction getConsumeAction(ConsumableItem this, Actor actor){
        return new ConsumeAction(this);
    }

    /**
     * Method to implemented, responsible for what happens when the conumable item gets consumed by the actor
     * @param actor
     */
    void consumedBy(Actor actor);

    /**
     * Method to check if the consumable item can fade before its consumed within the game
     * @return
     */
    boolean canFade();
}
