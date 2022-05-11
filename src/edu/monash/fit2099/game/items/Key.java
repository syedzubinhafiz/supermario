package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.game.actions.EndGameAction;
import edu.monash.fit2099.game.enums.Status;

public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.END_GAME);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        this.addAction(new EndGameAction()); // add new action to unlock Princess & end the game
        return super.getPickUpAction(actor);
    }
}
