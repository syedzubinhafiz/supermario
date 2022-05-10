package edu.monash.fit2099.game.items;

/**
 * This class represents the Wallet objects in the edu.monash.fit2099.game
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.items
 */
public class Wallet {

    // attributes
    /**
     * The total balance of the wallet
     */
    private int totalBalance;


    /**
     * Constructor.
     * @param initialAmount initial amouunt the actor can have in its wallet
     */
    public Wallet(int initialAmount){
        this.totalBalance=1200;
    }


    /**
     * Method to reduce the total balance in wallet
     * @param amount amount to reduce by
     * @return true if reduce was successful, else false
     */
    public boolean reduceBalance(int amount) {
        int offset = this.getTotalBalance()-amount;
        if (offset >=0) {
            this.setTotalBalance(offset);
            return true;
        }
        return false;
    }

    /**
     * Method to add the total balance in wallet
     * @param amount amount to add by
     */
    public void addBalance(int amount) {
        this.setTotalBalance(this.totalBalance+amount);
    }

    /**
     * Getter for the total balance
     * @return int total balance of wallet
     */
    public int getTotalBalance() {
        return totalBalance;
    }

    /**
     * Setter for the total balance
     * @param totalBalance balance
     */
    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }


}
