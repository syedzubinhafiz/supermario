package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.ConsumableItem;
import game.items.PowerStar;
import game.items.SuperMushroom;

public class ConsumeAction extends Action {

    private int maxHealthIncrease;
    private int healthHealAmt;

    private  char charChange;

    private Status buffStatus;

    private ConsumableItem consumableItem;


    public ConsumeAction (SuperMushroom superMushroom, Player player  ) {

        consumableItem = superMushroom;
        maxHealthIncrease = superMushroom.getHealthIncrease();
        charChange = superMushroom.getCharChange();
        buffStatus = superMushroom.getBuffStatus();

    }


    public ConsumeAction (PowerStar powerStar, Player player ) {

        consumableItem = powerStar;
        healthHealAmt = powerStar.getHealthHealAmt();
        buffStatus = powerStar.getBuffStatus();

    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if ( consumableItem instanceof SuperMushroom ) {
            ((Player)actor).increaseMaxHp( maxHealthIncrease );
            ((Player)actor).callSetDisplayChar( charChange );
            ((Player)actor).addCapability( buffStatus );
            ((Player)actor).removeItemFromInventory((Item)consumableItem);
        }

        if ( consumableItem instanceof  PowerStar ) {
            ((Player)actor).heal( healthHealAmt );
            ((Player)actor).addCapability( buffStatus );

            //This line resets the fading time of the powerStar after consumption. isConsumed is still needed to allow logic in PowerStar to remove the
            //Status away from the player once the fading duration has been reached.
            ((PowerStar)consumableItem).setFadingTimeOnPlayer( 0 );
            ((PowerStar)consumableItem).setIsConsumed( true );
        }

        return null;
    }


    @Override
    public String menuDescription(Actor actor) {
        return consumableItem + "has been consumed by the player!!!";
    }
}
