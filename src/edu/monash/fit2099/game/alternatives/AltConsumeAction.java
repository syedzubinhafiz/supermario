package edu.monash.fit2099.game.alternatives;

package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.FadeableItem;


public class AltConsumeAction extends PickUpItemAction {

    //attributes

    protected final AltConsumableItem consumableItem;


    public AltConsumeAction (AltConsumableItem item) {
        super((Item) item);
        this.consumableItem = item;

    }


    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.consumedBy(actor);

        if ( consumableItem.isInBottle() ){
            ((Player)actor).getBottle().removeWater();
            return actor +" consumed " + consumableItem+".";
            //can change to drinks
        }

        if ( consumableItem.isInInventory() ){
            map.locationOf(actor).removeItem((Item) consumableItem);
            return actor +" consumed " + consumableItem+".";
        }

        return actor +" consumed " + consumableItem+".";
    }


    @Override
    public String menuDescription(Actor actor) {
        String result= actor + " consumes "+ consumableItem ;
        if (consumableItem.canFade()) {
            result = actor + " consumes "+ consumableItem + " - " + ((FadeableItem) consumableItem).getFadingTimeOnFloorInventory() + " turns remaining";
        }
        return result;
    }
}

