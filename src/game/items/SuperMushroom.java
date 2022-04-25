package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.TradeAction;
import game.interfaces.Tradeable;

public class SuperMushroom extends Item implements Tradeable {

    private final int VALUE = 400;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
    public SuperMushroom() {
        super("Super Mushroom", '^', false);
    }

    @Override
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE, "b");
    }

    @Override
    public int getValue() {
        return VALUE;
    }

}
