package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.ConsumeAction;
import game.actions.TradeAction;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.ConsumableItem;
import game.interfaces.Tradeable;

import java.util.List;

public class SuperMushroom extends Item implements Tradeable, ConsumableItem {

    private final int VALUE = 400;
    private final int healthIncrease = 50;
    private final char charChange = 'M';
    private final Status buffStatus = Status.TALL;
    private boolean inInventory;


    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.inInventory=false;
    }

    @Override
    public ConsumeAction getConsumeAction(SuperMushroom this, Actor actor){
        return new ConsumeAction(this, actor  );
    }


    @Override
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE, "b");
    }

    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        //add a consumeAction after picking up
        addAction(getConsumeAction(actor));
        return super.getPickUpAction(actor);
    }

    @Override
    public void consumedBy(Actor actor) {
        actor.increaseMaxHp( healthIncrease );
        ((Player)actor).callSetDisplayChar( charChange );
        actor.addCapability( buffStatus );
        if (inInventory) {
            actor.removeItemFromInventory(this);
        }

    }
}
