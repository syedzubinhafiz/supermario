package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.Drinker;

public class PowerWater extends Water {

    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'p');
    }

    @Override
    public void consumedBy(Actor actor) {
        Drinker actorDrink = (Drinker) actor;
        actorDrink.setIntrinsicDamage( ((Drinker) actor).getIntrinsicDamage() + 15 );
        //Gives 15 damage to all drinkers

    }

    @Override
    public boolean canFade() {
        return false;
    }
}
