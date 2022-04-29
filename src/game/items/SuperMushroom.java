package game.items;

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

public class SuperMushroom extends Item implements Tradeable, ConsumableItem {

    private final int VALUE = 400;
    private final int healthIncrease = 50;
    private final char charChange = 'M';
    private final Status buffStatus = Status.TALL;
    private boolean inInventory;


//    /***
//     * Constructor.
//     *  @param name the name of this Item
//     * @param displayChar the character to use to represent this item if it is on the ground
//     * @param portable true if and only if the Item can be picked up
//     */
//    public SuperMushroom(String name, char displayChar, boolean portable) {
//        super(name, displayChar, portable);
//        this.inInventory=false;
//    }

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.inInventory=false;
    }

    @Override
    public ConsumeAction getConsumeAction(SuperMushroom this, Player player){
        return new ConsumeAction(this, player  );
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
        return new ConsumeAction(this,actor);
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
