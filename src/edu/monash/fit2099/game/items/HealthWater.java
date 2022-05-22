package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing HealthWater, a specific type of Water object that heals players.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.items
 */
public class HealthWater extends Water {


    /***
     * Constructor.
     */
    public HealthWater() {
        super("Healing Water", 'h');
    }


    /**
     * Method responsible for performing what happens when the Actor consumes the item/object.
     * @param actor Actor consuming the item
     */
    @Override
    public void consumedBy(Actor actor) {
        actor.heal(50);

    }


    /**
     * Implemented method from ConsumableItem interface that returns True or False based on whether the item can disappear over time.
     * @return True if can disappear, False if can't.
     */
    @Override
    public boolean canFade() {
        return false;
    }

}

