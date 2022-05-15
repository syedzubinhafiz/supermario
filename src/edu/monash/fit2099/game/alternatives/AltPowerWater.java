package edu.monash.fit2099.game.alternatives;

import edu.monash.fit2099.engine.actors.Actor;

public class AltPowerWater {
    @Override
    public void consumedBy(Actor actor) {
        int val = ((Drinker)actor).getIntrinsicDamage() + 15
        ((Drinker)actor).setIntrinsicDamage( val );
        //Added instrinsic damage value

    }

    //Feel like we dont need a water abstract class

    @Override
    public boolean canFade() {
        return false;
    }
}
