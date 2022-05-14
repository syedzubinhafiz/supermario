package edu.monash.fit2099.game.alternatives;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.game.actions.ConsumeAction;

public interface ConsumableItem {


    default ConsumeAction getConsumeAction(ConsumableItem this, Actor actor){
        return new ConsumeAction(this);
    }


    void consumedBy(Actor actor);


    boolean canFade();


    boolean isInInventory();

    boolean isInBottle();

    //Alternatively

    //Can use either pair to make use of consumable item instead of having to make many many classes, which makes the UML diagrams very difficult to draw and more cluttered.
    //See consumeAction for implementation
//    boolean isWater();
//    boolean isNotWater();

//    boolean isInInventory();
//    boolean isInBottle();


}

