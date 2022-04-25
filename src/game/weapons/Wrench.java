package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.TradeAction;
import game.interfaces.Tradeable;

public class Wrench extends WeaponItem implements Tradeable {

    private final int VALUE = 200;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Wrench(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }
    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
    }

    @Override
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE, "c");
    }

    @Override
    public int getValue() {
        return VALUE;
    }

}