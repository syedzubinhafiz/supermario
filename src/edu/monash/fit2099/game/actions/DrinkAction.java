package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

public class DrinkAction extends ConsumeAction {

    MagicalFountain fountain;
    /**
     * Constructor
     */
    public DrinkAction(ConsumableItem item, MagicalFountain fountain) {
        super(item);
        this.fountain=fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // consumed by
        consumableItem.consumedBy(actor);
        // remove water from fountain
        fountain.removeWater();
        return actor + " drank from " + fountain;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
