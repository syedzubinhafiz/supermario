package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.TradeAction;
import game.enums.Status;
import game.interfaces.Tradeable;

/**
 * This class represents the Wrench weapon in the game
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.weapons
 */
public class Wrench extends WeaponItem implements Tradeable {

    /**
     * Constant for the monetary value of the Wrench
     */
    private final static int VALUE = 200;

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

    /**
     * Constructor
     */
    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
    }

    @Override
    /**
     * Returns the trade action for this Wrench if needed
     * Implements the method from Tradeable interface
     * @see Tradeable#getTradeAction()
     */
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE);
    }

    @Override
    /**
     * Returns a new Wrench item for the seller to replenish its inventory
     * Implements the method from Tradeable interface
     * @see Tradeable#newInstance()
     */
    public Tradeable newInstance() {
        return new Wrench();
    }

    @Override
    /**
     * Overrides weaponitem's getPickUpAction so that we can add capability statsu of has_wrench to actor
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        this.addCapability(Status.HAS_WRENCH);
        return super.getPickUpAction(actor);
    }

    @Override
    /**
     * Returns the value of the Wrench
     * Implements the method from Tradeable interface
     * @see Tradeable#getValue()
     */
    public int getValue() {
        return VALUE;
    }

}
