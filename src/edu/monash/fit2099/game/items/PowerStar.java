package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.TradeAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.interfaces.FadeableItem;
import edu.monash.fit2099.game.interfaces.Tradeable;

/**
 * This class represents the PowerStar objects in the edu.monash.fit2099.game
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.items
 */
public class PowerStar extends Item implements Tradeable, ConsumableItem, FadeableItem {


    /**
     * Constant for the monetary value of a PowerStar
     */
    private final static int VALUE = 600;
    /**
     * Constant for the amount a powerstar can heal an actor that consumes it for
     */
    private final static int HEALTH_HEAL_AMT = 200;
    /**
     * Constant for the buff that this item provides if consumed
     */
    private final static Status BUFF_STATUS = Status.INVINCIBLE;
    /**
     * int for the turns left for PowerStar on either floor/inventory (we assume that the counter is regardless it is on the ground or in the actor's inventory)
     */
    private int fadingTimeOnFloorInventory;
    /**
     * int for the PowerStar's effect's remaining turns on player after being consumed
     */
    private int fadingTimeOnPlayer;
    /**
     * boolean for whether the PowerStar has been consumed or not
     */
    private boolean isConsumed;
    /**
     * ConsumeAction of the PowerStar instance
     */
    private ConsumeAction consumeAction;


    /**
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        fadingTimeOnFloorInventory = 10;
        fadingTimeOnPlayer = 10;
        isConsumed=false;
        consumeAction=null;
    }

    @Override
    /**
     * Returns the trade action for this powerstar if needed
     * Implements the method from Tradeable interface
     * @see Tradeable#getTradeAction()
     */
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE);
    }

    @Override
    /**
     * Returns a new PowerStar item for the seller to replenish its inventory
     * Implements the method from Tradeable interface
     * @see Tradeable#newInstance()
     */
    public Tradeable newInstance() {
        return new PowerStar();
    }

    @Override
    /**
     * Returns true since this Consumable item can fade within the edu.monash.fit2099.game
     * Implements the method from Consumable interface
     * @see ConsumableItem#canFade()
     */
    public boolean canFade() {
        return true;
    }

    @Override
    /**
     * Returns the value of the PowerStar
     * Implements the method from Tradeable interface
     * @see Tradeable#getValue()
     */
    public int getValue() {
        return VALUE;
    }


    /**
     * Getter for the status buff that PowerStar can provide
     * @return Status INVINCIBLE
     */
    public Status getBuffStatus(){
        return BUFF_STATUS;
    }

    /**
     * Getter for turns left before it fades
     * @return int for turns left
     */
    public int getFadingTimeOnFloorInventory(){
        return fadingTimeOnFloorInventory;
    }


    /**
     * Setter for if the item was consumed
     * @param isConsumed whether or not its consumed
     */
    public void setIsConsumed( boolean isConsumed ){
        this.isConsumed = isConsumed;
    }


    /**
     * Tick method that checks for what the PowerStar should do when in the inventory of the actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        // if not yet consumed and powerstar doesn't have the effect turned on,
        // then add a consume action to the actor
        if (!isConsumed && !actor.hasCapability(BUFF_STATUS)) {
            if(this.consumeAction !=null){
                removeAction(this.consumeAction);
            }
            this.consumeAction = getConsumeAction(actor);
            addAction(consumeAction);
        }

        // if not consumed, decrement the turns left before it fades.
        if (!isConsumed) {
            fadingTimeOnFloorInventory -= 1;
            if (fadingTimeOnFloorInventory < 0) {
                actor.removeItemFromInventory(this);
            }
        }
        // if already consumed, remove the consumeAction if it was there for the previous turn
        // and decrement the turns left for powerStar effect.
        else {
            if(this.consumeAction != null) {
                removeAction(this.consumeAction);
                this.consumeAction=null;
            }
            fadingTimeOnPlayer -= 1;
        }
        // if effect has worn off, remove from inventory
        if ( fadingTimeOnPlayer < 0 && isConsumed ) {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Tick method that checks for what the PowerStar should do when on a ground in the map
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        // decrement the turns left before it fades
        fadingTimeOnFloorInventory -= 1;
        if ( fadingTimeOnFloorInventory <= 0 ) {
            currentLocation.removeItem( this );
        }
    }



    @Override
    /**
     * Method to perform what happens when PowerStar is consumed by actor
     * @see ConsumableItem#consumedBy(Actor)
     */
    public void consumedBy(Actor actor) {
        actor.heal(HEALTH_HEAL_AMT);
        setIsConsumed(true);
        this.addCapability(Status.INVINCIBLE);

    }

}
