package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;

public class HealthWater extends Water {


    /***
     * Constructor.
     */
    public HealthWater() {
        super("Healing Water", 'h');
    }

    @Override
    public void consumedBy(Actor actor) {
        actor.heal(50);

    }

    @Override
    public boolean canFade() {
        return false;
    }
}
