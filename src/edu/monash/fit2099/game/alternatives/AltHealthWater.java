package edu.monash.fit2099.game.alternatives;

package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.items.Water;

public class AltHealthWater extends Item implements AltConsumableInterface {

    public AltHealthWater(){
        super("Water", 'z', false);
    }


    @Override
    public void consumedBy(Actor actor) {
        actor.heal(50);
    }

    @Override
    public boolean isInInventory(){
        return true;
    }

    public boolean isInBottle(){
        return false;
    }

    @Override
    public boolean canFade() {
        return false;
    }


    //Will have to change to implement isininv() and isinbot() to powerstar and supermushroom
}

