package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.ConsumableItem;
import game.interfaces.FadeableItem;

public class ConsumeAction extends PickUpItemAction {


    private ConsumableItem consumableItem;

    public ConsumeAction (ConsumableItem item) {
        super((Item) item);
        this.consumableItem = item;

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
