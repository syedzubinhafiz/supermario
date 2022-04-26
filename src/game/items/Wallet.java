package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Toad;
import game.interfaces.Tradeable;

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

        // get tradeAction for each tradeable item in toad's inventory & add to actionlist of wallet
        for (Tradeable item : Toad.getTradeableInventory()) {
            addAction(item.getTradeAction());
        }
    }
    public Wallet(){
        super("Wallet", 'u', false);
        if (Toad.getInstance()!=null && (Toad.getInstance().getTradeableInventory().size() > 0)) {
        for (Tradeable item : Toad.getInstance().getTradeableInventory()) {
            addAction(item.getTradeAction());
        }}
    }


    public boolean reduceBalance(int amount) {
        int offset = this.getTotalBalance()-amount;
        if (offset >=0) {
            this.setTotalBalance(offset);
            return true;
        }
        return false;
    }

    public void addBalance(int amount) {
        this.setTotalBalance(this.totalBalance+amount);
    }


    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }


}
