package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Bottle;

public class DrinkFromBottleAction extends ConsumeAction {

    public Bottle bottle;
    /**
     * Constructor
     */
    public DrinkFromBottleAction(ConsumableItem item, Bottle bottle) {
        super(item);
        this.bottle=bottle;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        bottle.removeWater();
        return actor + " has drank from his bottle.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "consumes water from bottle.";
    }
}
