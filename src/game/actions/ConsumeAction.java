package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.ConsumableItem;
import game.interfaces.FadeableItem;

public class ConsumeAction extends PickUpItemAction {


    private ConsumableItem consumableItem;
    private String message;

    public ConsumeAction (ConsumableItem item) {
        super((Item) item);
        this.consumableItem = item;

    }


    @Override
    public String execute(Actor actor, GameMap map) {

        consumableItem.consumedBy(actor);
        map.locationOf(actor).removeItem((Item) consumableItem);

//        if ( consumableItem instanceof SuperMushroom ) {
//            ((Player)actor).increaseMaxHp( maxHealthIncrease );
//            ((Player)actor).callSetDisplayChar( charChange );
//            ((Player)actor).addCapability( buffStatus );
//            ((Player)actor).removeItemFromInventory((Item)consumableItem);
//        }

//        if ( consumableItem instanceof  PowerStar ) {
//            ((Player)actor).heal( healthHealAmt );
//            ((Player)actor).addCapability( buffStatus );
//
//            //This line resets the fading time of the powerStar after consumption. isConsumed is still needed to allow logic in PowerStar to remove the
//            //Status away from the player once the fading duration has been reached.
//            ((PowerStar)consumableItem).setFadingTimeOnPlayer( 0 );
//            ((PowerStar)consumableItem).setIsConsumed( true );
//        }

        //Updated to show consumable item name properly.
        return actor +" consumed " + consumableItem.getConsumableName() +".";
    }



    @Override
    public String menuDescription(Actor actor) {
        String result= actor + " consumes "+ consumableItem ;
        if (consumableItem instanceof FadeableItem) {
            result = actor + " consumes "+ consumableItem + " - " + ((FadeableItem) consumableItem).getFadingTimeOnFloorInventory() + " turns remaining";
        }
        return result;
    }
}
