#### REQ 5 CLASS RESPONSIBILITIES

New classes added for REQ5:
Wallet, TradeAction, Coin, AddToWalletAction, Toad, TradeableItem.

Note that in the UML Class diagram for REQ5 that the yellow highlighted classes represent the modified/added classes for REQ5 specifically.
Classes that are black represent classes existing in the basecode.
Classes that have other colours (other than black & yellow) are classes added/modifed for OTHER REQs.
  

**Player**
1. New methods added:

   getWallet() to return its Wallet from its inventory.
   

**Wallet**
1. Class Overall Responsibility:

    This class is used to implement a wallet system where each player can have an assigned wallet item that contains coins and monetary value 
    for trading items within the game.
    It is also used to get the total balance of value of coins that the player has during the game.
    This assigned item should automatically be in the inventory list of the player during player construction,
    since it is fixed and cannot be removed from every player.

2. Relationship with other classes:
    
    Inherits from Item abstract class (extends this class).
    Dependency on TradeAction class.
    Association with Coin class.

3. Attributes: 

    private int totalBalance,

    private ArrayList<Coin> coins,
    
    *When instantiated, it will NOT be portable (boolean inherited from item abstract class)
    
4. Constructor: 
    
    Creates instances of tradeAction, one for each of Toad's tradeableItems in its inventory.
    These tradeActions are added to the list of allowable actions for the Wallet item.
    
5. Methods: 
    private getBalance() to return the balance
    private addCoin() to add a collected coin to its arraylist
    private useCoins() to use coins in wallet to purchase an item
    
    
**TradeAction**
1. Class Overall Responsibility:

    This class is used to implement an action where each player can trade some coins to purchase an item from Toad actor.

2. Relationship with other classes:
    
    Inherits from Action abstract class (extends this class).
    Has a association on Player class & TradeableItem interface.
    Has a dependency on singleton Toad class.

3. Attributes: 
    
    private Player player;
    private TradeableItem itemToTrade;
    private int tradeValue;
    private String hotkey;
    
4. Constructor: 
    
    creates instance of tradeAction with a reference to the item to be traded, and the player making the trade.
 
5. Methods: 
    overrides Action's execute method
        
    in public execute() method:
    
    - Retrives wallet from Player
    
    - Retrieves coins from Player's wallet (if balance is enough for the item)
    
    - Passes on coins to Toad instance
    
    - Adds the item that was purchased to player's inventory
    
    overrides Action's getHotKey() method to return the specific hotkey attribute it has.
    

**Coin**
1. Class Overall Responsibility:

    This class is used to represent a Coin item (that is spawned from Sapling class), that can be added to the Wallet item of a player,
    or to the inventory of the Toad.

2. Relationship with other classes:
    
    Inherits from Item abstract class (extends this class).
    Wallet, has an association with Coin class.

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
    
    overrides Item's getDropActionItemAction() method
    
        - to return null;
    

    
    
**AddToWalletAction**
1. Class Overall Responsibility:

    This class is used to represent an adding item to Wallet Action. Note that this class will only ever be used for adding coins to the wallet.

2. Relationship with other classes:
    
    Inherits from PickUpItemAction class (extends this class).

3. Attributes: 

    private final Item item, as inherited from PickUpItemAction class 
    
4. Constructor: 
    
    creates instance of AddToWalletAction with the coin item attached to it.
 
5. Methods: 
    overrides PickUpItemActions's execute method
        
    in public execute() method:
   
    - access the Actor's wallet and add the coin item into the Actor's wallet item.
    
    

**Toad**
1. Class Overall Responsibility:

    This class is a singleton class since each game instantiated should only ever have one Toad. It is responsible for representing a Toad
    who is friendly and who sells several items (for now: wrench, supermushroom, powerstar) to players.
    
2. Relationship with other classes:
    
    TradeAction has a dependency with this class.
    Extends Actor asbtract class.

3. Attributes: 
    
    private static Toad instance;
    
    public ArrayList<Coin> coins;
    
    
4. Constructor:
    
    a private constructor that creates an instance of Toad, and instantiates PowerStar, SuperMushroom & Wrench items 
    which are added to its inventory arraylist of items.
 
5. Methods: 

    getInstance() method for instantiating a new instance if one hasn't yet been instantiated, else it returns the first instantiated Toad instance.
    
    addCoins() method for receiving an arraylist of coins and adding those coins to Toad's own coins list. 
    
    getTradeableItems() method for returning an arraylist of items Toad can trade.
    
    purchaseItem(itemToTrade, usedCoinsList) method for 
    - removing the itemToTrade from the Toad's inventory, and 
    adding a new instance of the type of that item traded into the Toad's inventory.
    - adding the used Coin instances to Toad's inventory
            
            
**TradeableItem**
1. Class Overall Responsibility:

    This class is an interface that represents the functionality of an item being tradeable.
    
2. Relationship with other classes:
    
    TradeAction has an association with TradeableItem.
    Wench, SuperMushroom and PowerStar (as of now only these 3) implements TradeableItem.

3. Methods:
    getTradeAction();
    This is to get the TradeAction of the current TradeableItem item object.
    