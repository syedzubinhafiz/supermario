package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.AddToWalletAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Resettable;

/**
 * This class represents the Coin objects in the edu.monash.fit2099.game
 *
 * @author  Syed Zubin Hafiz
 * @version 1.0.0
 * @see edu.monash.fit2099.game.items
 */
public class Coin extends Item implements Resettable {
    /**
     * Private Attributes
     */

    /**
     * An integer variable which is assigned to value of a coin
     */
    private final int coinValue;

    /**
     * Constructor of Coin
     * Initializes the coinValue object
     * @param coinValue value of the coin
     */
    public Coin(int coinValue) {
        super("Coin", '$', false);
        this.coinValue = coinValue;
        Resettable.super.registerInstance();
    }

    /**
     * Used to access the value of Coin
     * @return int coin value
     */
    public int getCoinValue() {
        return coinValue;
    }

    @Override
    /**
     * This method allows actor to pick up the item on the ground and return it to an instance of addToWallet action
     * @param actor Actor who picks up the item
     * @return instance
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new AddToWalletAction(this);
    }

    @Override
    /**
     * Sets a RESET capability in the enum Status class to invoke the Reset functionality on the Coin class
     */
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    //for coins ON THE GROUND (those picked up
    @Override
    /**
     * This method overrides the tick method to implement the RESET functionality where all items on the ground are removed
     * @param currentlocation
     *
     */
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
        }
        else {
            super.tick(currentLocation);
        }
    }

}
