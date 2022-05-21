package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.ConsumableItem;

public class ConsumeInventoryItemAction extends ConsumeAction {

    public ConsumeInventoryItemAction ( ConsumableItem item ) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        map.locationOf(actor).removeItem((Item) consumableItem);
        return actor +" consumed " + consumableItem+".";
    }


}
