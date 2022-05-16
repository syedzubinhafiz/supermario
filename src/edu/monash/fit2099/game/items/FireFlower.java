package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;


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

    @Override
    public boolean canFade() {
        return false;
    }

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
        else if ( isConsumed && this.consumeAction != null) {
            removeAction(this.consumeAction);
            this.consumeAction = null;
        }
        fadingTimeOnPlayer -= 1;

        // if effect has worn off, remove from inventory
        if (fadingTimeOnPlayer < 0 && isConsumed) {
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public void consumedBy(Actor actor) {
        setIsConsumed(true);
        this.addCapability(FIRE_ATTACK);
    }


}
