package game.items;

public class Wallet {

    // attributes
    private int totalBalance;


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
