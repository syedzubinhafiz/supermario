package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.interfaces.Drinker;


/**
 * Class representing PowerWater, a specific type of Water object that increases the Actor's damage.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.items
 */
public class PowerWater extends Water {

    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'p');
    }


    /**
     * Method responsible for performing what happens when the Actor consumes the item/object.
     * @param actor Actor consuming the item
     */
    @Override
    public void consumedBy(Actor actor) {
        Drinker actorDrink = (Drinker) actor;
        actorDrink.setIntrinsicDamage( ((Drinker) actor).getIntrinsicDamage() + 15 );
        //Gives 15 damage to all drinkers

    }


    /**
     * Implemented method from the ConsumableItem interface that returns True or False based on whether the item can disappear over time.
     * @return True if can disappear, False if can't
     */
    @Override
    public boolean canFade() {
        return false;
    }

}

