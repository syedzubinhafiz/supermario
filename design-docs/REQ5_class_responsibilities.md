#### REQ 5 CLASS RESPONSIBILITIES

**Wallet**
1. Class Overall Responsibility:

    This class is used to implement a wallet system where each player can have an assigned wallet item that contains coins and monetary value for trading items within the game.
    It is also used to get the total balance of value of coins that the player has during the game.
    This assigned item should not be in the inventory list of the player since it is fixed and cannot be removed
    from every player.

2. Relationship with other classes:
    
    Inherits from Item abstract class (extends this class).
    Player has an association with Wallet class

3. Attributes: 

    private int totalBalance, 

    private ArrayList<Coin> coins,
    
4. Constructor: 
    
    Creates 3 instances of tradeAction (with trade amount), one for each of Wrench, SuperMushroom & Powerstar

    These tradeActions are added to the list of allowable actions for the Wallet item.
    
5. Methods: 
    private getBalance() to return the balance
    private addCoin() to add a collected coin to its arraylist
    private useCoins() to use coins in wallet to purchase an item
    
    
**Trade Action**
1. Class Overall Responsibility:

    This class is used to implement an action where each player can trade some coins to purchase an item from Toad actor.

2. Relationship with other classes:
    
    Inherits from Action abstract class (extends this class).
    Has a association on Actor (player) class.
    Has a dependency on Wallet class.
    Has a dependency with the singleton Toad class.

3. Attributes: 
    
    private Player player;
    private String itemToTrade, 
    
4. Constructor: 
    
    creates instance of tradeAction with a reference to a String representation of the item to be traded.
 
5. Methods: 
    overrides Action's execute method
        
    in public execute() method:
   
    - Get itemToTrade's worth (item's constant worthValue)
    
    - Retrieves coins from Player's wallet (if balance is enough for the item)
    
    - Passes on coins to Toad instance
    
    - Adds a new item that was purchased to player's inventory
    

**Coin**
1. Class Overall Responsibility:

    This class is used to represent a Coin item (that is spawned from Sapling class), that can be added to the Wallet item of a player.

2. Relationship with other classes:
    
    Inherits from Item abstract class (extends this class).
    Wallet has an association with Coin class.

3. Attributes: 

    private int coinValue, 
    
4. Constructor: 
    
    creates instance of Coin with the value of the coin attached to it.
 
5. Methods: 
    overrides Item's getPickItemAction() method
        
    in getPickItemAction() method:
   
    - instantiates AddToWalletAction object INSTEAD OF a PickUpItemAction, because PickUpItemAction adds the item to the inventory of the player
    
      Thus, AddToWalletAction will help to add the Coin to the Player's wallet instead of its inventory.
    
    - returns the AddToWalletAction object
    
**AddToWalletAction**
1. Class Overall Responsibility:

    This class is used to represent an adding item to Wallet Action. Note that this class will only ever be used for adding coints to the wallet.

2. Relationship with other classes:
    
    Inherits from PickUpItemAction class (extends this class).

3. Attributes: 

    private final Item item, as inherited from PickUpItemAction class 
    
4. Constructor: 
    
    creates instance of AddToWalletInstance with the coin item attached to it.
 
5. Methods: 
    overrides PickUpItemActions's execute method
        
    in public execute() method:
   
    - access the Actor's wallet and add the coin item into the Actor's wallet item.
    
    

**Toad**
1. Class Overall Responsibility:

    This class is a singleton class since each game instantiated should only ever have one Toad. It is responsible for representing a Toad
    who is friendly and who sells several items (wrench, supermushroom, powerstar) to players.
    
2. Relationship with other classes:
    
    TradeAction has a depencency with this class.

3. Attributes: 

    public static final ArrayList<String> monologues;
    
    private static Toad instance;
    
    public ArrayList<Coin> coins;
    
    
4. Constructor:
    
    a private constructor that creates an instance of Toad.
 
5. Methods: 

    getInstance() method for instantiating a new instance if one hasn't yet been instantiated, else it returns the first instantiated Toad instance.
    
    addCoins() method for receiving an arraylist of coins and added those coins to Toad's own coins list    
    