package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.interfaces.Drinker;

public class PowerWater extends Water {

    @Override
    public void consumedBy(Actor actor) {
        Drinker actorDrink = (Drinker) actor;
        actorDrink.setIntrinsicDamage();
    }

    @Override
    public boolean canFade() {
        return false;
    }
}
