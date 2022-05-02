package game.items;

public class Wallet {

    // attributes
    private int totalBalance;

    /***
//     * Constructor.
//     *  @param name the name of this Item
//     * @param displayChar the character to use to represent this item if it is on the ground
//     * @param portable true if and only if the Item can be picked up, set to false for Wallet for player class
//     */
    public Wallet(int initialAmount){
        this.totalBalance=initialAmount;
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
