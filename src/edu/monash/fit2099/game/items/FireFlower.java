package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

/**
 * FireFlower class represents all the functionalities the fireflower has within the game
 * @author: Syed Zubin Hafiz
 * @version: 1.0.0
 * @see edu.monash.fit2099.game.items
 */

public class FireFlower extends Item implements ConsumableItem {

    private final static Status FIRE_ATTACK = Status.FIRE_ATTACK_EFFECT;
    /**
     * int for the FireFlower's effect's remaining turns on player after being consumed
     */
    private int fadingTimeOnPlayer;

    /**
     * boolean for whether the FireFlower has been consumed or not
     */
    private boolean isConsumed;
    /**
     * ConsumeAction of the FireFlower instance
     */
    private ConsumeAction consumeAction;

    /**
     * Constructor.
     */
    public FireFlower() {
        super("FireFlower", 'f', true);
        fadingTimeOnPlayer = 20;
        isConsumed = false;
        consumeAction = null;
    }

    /**
     * Setter for if the item was consumed
     *
     * @param isConsumed determines whether its consumed
     */
    public void setIsConsumed(boolean isConsumed) {
        this.isConsumed = isConsumed;
    }


    /**
     * A method which decides if the player keeps an item in his inventory, which can fade with turns.
     * @return
     */
    @Override
    public boolean canFade() {
        return false;
    }
    /**
     * method to check if item on the current ground location has been consumed.
     * We initially check if the item has been consumed.
     * If not yet consumed, we add the consume action for the actor.
     * If item has been consumed, we remove the option for the actor, given it was there on the previous turn
     * We also keep track of the fading time. Once the limited number of turns has been reached, the item is removed
     * from the actor's inventory.
     * @param currentLocation of the actor
     * @param  actor who picks up the flower
     */
    public void tick(Location currentLocation, Actor actor) {
        // if not yet consumed and the Fire Attack effect hasn't been put to use,
        // then add a consume action to the actor
        if (!isConsumed && !actor.hasCapability(FIRE_ATTACK)) {
            if (this.consumeAction != null) {
                removeAction(this.consumeAction);
            }
            this.consumeAction = getConsumeAction(actor);
            addAction(consumeAction);
        }
        // if already consumed, remove the consumeAction if it was there for the previous turn
        // and decrement the turns left for fire attack effect.
        else if (isConsumed && this.consumeAction != null) {
            removeAction(this.consumeAction);
            this.consumeAction = null;
        }
        fadingTimeOnPlayer -= 1;

        // if effect has worn off, remove from inventory
        if (fadingTimeOnPlayer < 0 && isConsumed) {
            actor.removeItemFromInventory(this);
        }
    }


    /**
     * Method to add capability to the actor who consumed the FireFlower item.
     * @param actor consuming the item
     */
    @Override
    public void consumedBy(Actor actor) {
        setIsConsumed(true);
        this.addCapability(FIRE_ATTACK);
        togglePortability();
    }


}
