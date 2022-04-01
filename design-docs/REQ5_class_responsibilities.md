#### REQ 5 CLASS RESPONSIBILITIES

**Wallet**
1. Class Overall Responsibility:

    This class is used to implement a wallet system where each player can have an assigned wallet item that contains coins and monetary value for trading items within the game.
    It is also used to get the total balance of value of coins that the player has during the game.

2. Attributes: 

    private int totalBalance, 

    private ArrayList<Coin> coins,
    
    
3. Constructor: 
    
    Creates 3 instances of tradeAction (with trade amount), one for each of Wrench, SuperMushroom & Powerstar

    These tradeActions are added to the list of allowable actions for the Wallet item.
    
  
4. Methods: 
    private getBalance() to return the balance
    private addCoin() to add a collected coin to its arraylist
    private useCoins() to use coins in wallet to purchase an item
    
    

