package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.items.Bottle;
import edu.monash.fit2099.game.items.Water;

public class RefillBottleAction extends Action {

    private Water water;
    private MagicalFountain fountain;

    public RefillBottleAction(Water water, MagicalFountain fountain){
        this.water = water;
        this.fountain = fountain;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        // refills the bottle of player
        Bottle.getInstance().addWater(water);
        fountain.removeWater(); // remove the water from fountain
        return "Mario refills bottle from " + fountain + " (" + fountain.getCapacity()+ "/"+fountain.getMaxCapacity()+")";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Refill " + actor + "\'s bottle.";
    }
}
