package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.actors.Toad;
import edu.monash.fit2099.game.interfaces.Obtainable;
import edu.monash.fit2099.game.items.Bottle;

/**
 * Special Action class tasked with giving the player the ability to obtain Obtainable Items from Toad
 * The act of obtaining an item requires no money, only an interaction with Toad.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.engine.actions
 */
public class ObtainedAction extends Action {


    /**
     * Attribute
     * The obtainable item to be obtained.
     */
    private Obtainable item;


    /**
     * Constructor
     * @param item the item to obtain from Toad.
     */
    public ObtainedAction(Obtainable item){
        this.item = item;
    }


    /**
     * Executes the ObtainedAction method by using the items obtainedBy() method to make any changes required to the Player.
     * Removes item from Toad's obtainable inventory and adds it to the player's inventory.
     * @param actor The actor receiving the obtainable item.
     * @param map The map the actor is on.
     * @return a String to describe the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        item.obtainedBy(actor);
        actor.addItemToInventory((Item) item);
        // remove from toad
        Toad.removeObtainableItem(item);
        return item + " is obtained by " + actor;
    }


    /**
     * Returns a descriptive statement about ObtainedAction for menu use.
     * @param actor The actor performing the action.
     * @return a String describing the actor obtaining the item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains " + item;
    }
}
