package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;

import java.util.List;

public class Wallet extends Item {

    // attributes
    private int totalBalance;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up, set to false for Wallet for player class
     */
    public Wallet(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.totalBalance=0;

        //creates instances of tradeAction, once for each of Toad's tradableItems

        // add these instances to actionslist using addAction function
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public boolean reduceBalance(int amount) {
        int offset = this.getTotalBalance()-amount;
        if (offset >=0) {
            this.setTotalBalance(this.getTotalBalance()-amount);
            return true;
        }
        return false;
    }

    public void addBalance(int amount) {
        this.setTotalBalance(this.getTotalBalance()+amount);
    }
}
