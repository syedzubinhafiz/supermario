package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

public class DrinkFromFountainAction extends ConsumeAction {

    MagicalFountain fountain;

    /**
     * Constructor
     */
    public DrinkFromFountainAction(ConsumableItem item, MagicalFountain fountain) {
        super(item);
        this.fountain=fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // remove water from fountain
        fountain.removeWater();
        return actor + " drank from " + fountain;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
