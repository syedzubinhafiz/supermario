package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.interfaces.ConsumableItem;

public class ConsumeAction extends PickUpItemAction {


    private ConsumableItem consumableItem;


    public ConsumeAction (Item item, Actor actor ) {
        super(item);
        this.consumableItem = (ConsumableItem) item;
//        this.maxHealthIncrease = ((ConsumableItem)item).getHealthIncrease();
//        this.charChange = item.getCharChange();
//        this.buffStatus = item.getBuffStatus();

    }


//    public ConsumeAction (PowerStar powerStar, Player player ) {
//
//        consumableItem = powerStar;
//        healthHealAmt = powerStar.getHealthHealAmt();
//        buffStatus = powerStar.getBuffStatus();
//
//    }

    @Override
    public String execute(Actor actor, GameMap map) {

        consumableItem.consumedBy(actor);
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

        return "You have consumed the "+consumableItem;
    }


    @Override
    public String menuDescription(Actor actor) {
        return consumableItem + "has been consumed by the player!!!";
    }
}