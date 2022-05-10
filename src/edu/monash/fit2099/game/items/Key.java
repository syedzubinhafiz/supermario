package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.game.actions.EndGameAction;

public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        this.addAction(new EndGameAction()); // add new action to unlock game
        return super.getPickUpAction(actor);
    }
}
