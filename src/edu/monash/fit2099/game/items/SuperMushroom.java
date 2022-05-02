package edu.monash.fit2099.game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.TradeAction;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.Tradeable;

import static java.lang.Character.toUpperCase;

/**
 * This class represents the SuperMushroom objects in the edu.monash.fit2099.game
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.items
 */
public class SuperMushroom extends Item implements Tradeable, ConsumableItem {
    /**
     * Constant for the monetary value of a SuperMushroom
     */
    private final static int VALUE = 400;
    /**
     * Constant for the amount a SuperMushroom can heal an actor that consumes it for
     */
    private final static int HEALTH_INCREASE = 50;
    /**
     * Constant for the buff that this item provides if consumed
     */
    private final static Status BUFF_STATUS = Status.TALL;
    /**
     * boolean for whether the SuperMushroom has been consumed or not
     */
    private boolean isConsumed;
    /**
     * ConsumeAction of the SuperMushroom instance
     */
    private ConsumeAction consumeAction;

    /**
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        isConsumed=false;
        consumeAction=null;
    }

    @Override
    /**
     * Tick method that checks for what the SuperMushroom should do when in the inventory of the actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        //add a consumeAction after picking up
        if (!isConsumed) {
            if(this.consumeAction !=null){
                removeAction(this.consumeAction);
            }
            this.consumeAction = getConsumeAction(actor);
            addAction(this.consumeAction);
        }
        else {
            if(this.consumeAction != null) {
                removeAction(this.consumeAction);
                this.consumeAction=null;
            }
        }
        super.tick(currentLocation, actor);
    }


    @Override
    /**
     * Tick method that checks for what the SuperMushroom should do when on a ground in the map
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }

    @Override
    /**
     * Returns the trade action for this SuperMushroom if needed
     * Implements the method from Tradeable interface
     * @see Tradeable#getTradeAction()
     */
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE);
    }

    @Override
    /**
     * Returns a new SuperMushroom item for the seller to replenish its inventory
     * Implements the method from Tradeable interface
     * @see Tradeable#newInstance()
     */
    public Tradeable newInstance() {
        return new PowerStar();
    }

    @Override
    /**
     * Returns the value of the SuperMushroom
     * Implements the method from Tradeable interface
     * @see Tradeable#getValue()
     */
    public int getValue() {
        return VALUE;
    }


    @Override
    /**
     * Method to perform what happens when SuperMushroom is consumed by actor
     * @see ConsumableItem#consumedBy(Actor)
     */
    public void consumedBy(Actor actor) {
        actor.increaseMaxHp(HEALTH_INCREASE);
        ((Player)actor).callSetDisplayChar( toUpperCase(actor.getDisplayChar()) );
        actor.addCapability(BUFF_STATUS);
        setIsConsumed(true);
    }

    @Override
    /**
     * Returns false since this Consumable item cannot fade within the edu.monash.fit2099.game
     * Implements the method from Consumable interface
     * @see ConsumableItem#canFade()
     */
    public boolean canFade() {
        return false;
    }

    /**
     * Setter for if the SuperMushroom was consumed
     * @param b whether or not its consumed
     */
    private void setIsConsumed(boolean b) {
        isConsumed=b;
    }

}
