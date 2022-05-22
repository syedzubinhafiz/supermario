package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.game.actions.UnlockAction;
import edu.monash.fit2099.game.enums.Status;

/**
 * Key class represents the key to unlock princess peach
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.items
 */
public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.END_GAME);
    }

    @Override
    /**
     * This method allows actor to pick up the key and add the UnlockAction to the Key when picked up
     * @param actor Actor who picks up the item
     * @return instance
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        this.addAction(new UnlockAction()); // add new action to unlock Princess & end the game
        return super.getPickUpAction(actor);
    }
}
