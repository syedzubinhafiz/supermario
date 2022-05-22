package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.ConsumableItem;


/**
 * Extension of ConsumeAction class that allows Enemies to consume Water objects in a fountain when they stumble upon it.
 *
 * @author Chong Jin Yao & Team1
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class DrinkFromFountainAction extends ConsumeAction {


    /**
     * Attribute
     * Fountain at which the actor drinks from.
     */
    private MagicalFountain fountain;


    /**
     * Attribute
     * ConsumableItem to be consumed, for access to the second water object to be consumed.
     */
    private ConsumableItem secondWater;


    /**
     * Constructor
     * @param item the ConsumableItem to be consumed.
     * @param fountain the MagicalFountain from which the water is to be consumed.
     */
    public DrinkFromFountainAction(ConsumableItem item, MagicalFountain fountain) {
        super(item);
        this.fountain=fountain;
    }


    /**
     * Drinking requires two Water objects to be consumed at a time.
     * Executes base ConsumeAction class with super() to make use of consumedBy() to give enemy effects of
     * consuming item in fountain. Executes consumedBy() of second Water object if it exists.
     * Removes items assigned to attribute in current class and parent class from fountain object.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to let the Player know that another entity has successfully drank from the fountain.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        // remove water from fountain
        fountain.removeWater();
        // consume/remove water from fountain
        secondWater = fountain.removeWater();
        secondWater.consumedBy(actor);
        return actor + " drank from " + fountain;
    }


    /**
     * Returns a descriptive statement for DrinkFromFountainAction.
     * @param actor The actor performing the certain action.
     * @return a String describing a non-player entity has drank from the fountain.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Drink from " + fountain;
    }

}

