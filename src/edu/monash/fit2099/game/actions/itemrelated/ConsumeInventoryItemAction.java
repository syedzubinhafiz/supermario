package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.itemrelated.ConsumeAction;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.FadeableItem;

public class ConsumeInventoryItemAction extends ConsumeAction {

    public ConsumeInventoryItemAction ( ConsumableItem item ) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        consumableItem.consumedBy(actor);
        map.locationOf(actor).removeItem((Item) consumableItem);
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
