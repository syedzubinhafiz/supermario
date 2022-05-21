package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Bottle;

public class DrinkFromBottleAction extends ConsumeAction {

    public ConsumableItem secondWater;
    /**
     * Constructor
     */
    public DrinkFromBottleAction(ConsumableItem item) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // consume/remove first water
        super.execute(actor, map);
        Bottle.getInstance().removeWater();
        // consume/remove second water
        secondWater = Bottle.getInstance().removeWater();
        if(secondWater!=null) {
            secondWater.consumedBy(actor);
        }
        return actor + " has drank from his bottle.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Bottle"+Bottle.getInstance().getItems();
    }
}
