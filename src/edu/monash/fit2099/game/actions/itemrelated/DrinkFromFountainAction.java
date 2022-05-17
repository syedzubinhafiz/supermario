package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

public class DrinkFromFountainAction extends ConsumeAction {

    MagicalFountain fountain;
    public ConsumableItem secondWater;

    /**
     * Constructor
     */
    public DrinkFromFountainAction(ConsumableItem item, MagicalFountain fountain) {
        super(item);
        this.fountain=fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        // remove water from fountain
        fountain.removeWater();
        // consume/remove water from fountain
        secondWater = fountain.removeWater();
        secondWater.consumedBy(actor);
        return actor + " drank from " + fountain;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Drink from " + fountain;
    }
}
