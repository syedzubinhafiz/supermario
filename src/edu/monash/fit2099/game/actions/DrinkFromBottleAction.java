package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Bottle;


/**
 * Extension of ConsumeAction class that allows Players to consume consumable items stored in their bottle.
 *
 * @author Chong Jin Yao & Team1
 * @version 3.0.0
 * @see edu.monash.fit2099.engine.actions
 */
public class DrinkFromBottleAction extends ConsumeAction {

    /**
     * Attribute
     * Consumable item to be consumed, for access to second water object to be consumed.
     */
    public ConsumableItem secondWater;


    /**
     * Constructor
     * @param item First Water object to be consumed
     */
    public DrinkFromBottleAction(ConsumableItem item) {
        super(item);
    }


    /**
     * Bottle requires two Water objects to be consumed at a time.
     * Executes base ConsumeAction class with super() to make use of consumedBy() to give player effects of
     * consuming item in bottle. Executes consumedBy() of second Water object if it exists.
     * Removes items assigned to attribute in current class and parent class from player's bottle.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to let the Player know that he has successfully consumed the item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // consume/remove first water
        super.execute(actor, map);
        Bottle bottle = Bottle.getInstance();
        bottle.removeWater();
        // consume/remove second water
        secondWater = bottle.removeWater();
        if(secondWater!=null) {
            secondWater.consumedBy(actor);
        }
        return actor + " has drank from his bottle.";
    }


    /**
     * Returns a descriptive statement for DrinkFromBottleAction.
     * @param actor The actor performing the certain action.
     * @return a String to let the player know that he has successfully consumed the item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Bottle"+Bottle.getInstance().getItems();
    }

}
