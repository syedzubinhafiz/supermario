package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Bottle;
import edu.monash.fit2099.game.items.Water;

public class DrinkFromBottleAction extends ConsumeAction {

    public Bottle bottle;
    public ConsumableItem secondWater;
    /**
     * Constructor
     */
    public DrinkFromBottleAction(ConsumableItem item, Bottle bottle) {
        super(item);
        this.bottle=bottle;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // consume/remove first water
        super.execute(actor, map);
        bottle.removeWater();
        // consume/remove second water
        secondWater = bottle.removeWater();
        if(secondWater!=null) {
            secondWater.consumedBy(actor);
        }

        return actor + " has drank from his bottle.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Bottle"+bottle.getItems();
    }
}
