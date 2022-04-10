#### REQ 5 Design Rationale

**1: Purchasing an item: TradeAction and Wallet class**

tradeAction extends Action. This allows for the action to purchase an item to be done. 
We want tradeAction to inherit from Action, so that there is an "is-a" relationship that would allow for reusability of code & extensibility of tradeAction class.


Wallet extends Item. This would allow an actor who has a Wallet to use their Wallet item to perform this tradeAction action.


In this game, the ideal way to interact with the object is by attaching an appropriate action to its corresponding object 
(aligns with the meaning of "object-oriented").
Thus, this is shown with this: **Wallet ---<<create >>---> TradeAction** (association), and 
**Player ---<<stores>>---> Wallet** (association). 
The Wallet is the item object that gives the Actor (i.e., the Player) an action to purchase an item. 
Alternatively, we can add an attribute called totalBalance in Player class to account for the total amount 
he has received from collecting coins. 
However, doing this will require the Player to instantiate a tradeAction in its “playTurn” method in order 
to add it to the list of actions it can do. By doing so, this would generate an additional dependency for Player 
to TradeAction, and also “playTurn”  would have to do added functionality such as checking if the balance is enough for purchase. 
This goes against the principle of “Reduce Dependency” as well as “single-responsibility”, as it would have more dependencies and 
the Player class would be responsible for purchasing an item, which it shouldn’t be as it shouldn’t be responsible for the 
functionality of the coins/items it has. 


Hence, we discard this alternative. Our final design has now aligned with the Reduce Dependency Principle and Single-Responsibility Principle.
The application checks for actions allowed for the Wallet item stored in Player’s inventory, 
instead of Player adding the trading action to the action list inside the Player's own playTurn() function. 
Both designs are okay and do not break any object-oriented principles. 
We decided to create a Wallet item and add it to the Player's inventory because 
we ensure that the meaning of each class is clear and they can be reused and allows extensibility in other scenarios in the 
future (ie: if there is another actor who can store a Wallet item other than Player) (adheres to the DRY principle & SRP principle). 

**2: Coin, Wallet and AddToWalletAction**
Here, wallet is added to the player's inventory as a non-portable item. Whereas coin is added to the Wallet of the player rather than added to
the player's inventory. 
So, when the player is picking up a coin item from the ground, they will not perform the PickUpItemAction, as this action will add the coin
into the players inventory. Thus the AddToWalletAction that extends PickUpItemAction will be the action done when the coin item is picked up.


The reason why we do this is because:

1) We want only the Wallet system to have responsibility of dealing with coins and money of the player.
The player should not directly be taking care of the functionality of the coins and what we can do with them.
This is in line with the "Single Responsibility Principle" so that Player class won't have too many responsibilities.

2) With AddToWalletAction, this action class will be useful for future extensibility, if we have other forms of currency that can be added to the
Wallet of the player other than coins. 
This is in line with the "Open-Closed Principle", where this AddToWalletAction class can be extended to be used and to accomodate more functionality
in the future, without modifying the class. 
Also by adding coins to the wallet, we don't destroy the coins straight away (ie: just keep the balance). 
Even though keeping the coins is not part of the requirement, we believe that keeping these coins as "items" in the wallet
can help for future trading if the coins are to be used for other functionality besides just trading.

Therefore, our final design is in line with the  "Single Responsibility Principle" and  "Open-Closed Principle", and thus we have decided
to go with this design for these 3 classes.

** ADD: WHY IS WALLET NOT A SINGLETON CLASS
** ADD: WHY IS COIN IN COIN ARRAYLIST AND NOT DESTROYED
        - could be scoring system for the game later on
        

**3: Toad and Tradeable**
Tradeable is an interface by which the Toad singleton class keeps an arraylist of, and that the items or weapons that are tradeable will 
implement this interface.

An alternative to doing this is for Toad to have attributes of Wrench, SuperMushroom and PowerStar (one of each type), to store
the current items he can trade with the player. However, this causes the Toad to have 3 associations (strong dependency), one to each of 
the tradeable items. This goes against the principle of “Reduce Dependency”, as Toad class would have more dependencies.

Thus, in our current design, by having weapons, items or future classes implement "Tradeable" interface, 
Toad would only need to have an association to this Tradeable interface class by having an arraylist of Tradeable items!
This is in line with both the "Reduce Dependency Principle" and the "Dependency Inversion Principle", where we
are reducing the amount of dependencies/associations for Toad class (RDP) and we are making sure that the Toad concrete class
doesn't depend on other concrete classes (Wrench, SuperMushroom, Powerstar) and that Toad depends on an abstraction which is the interface.
Therefore, the above rationale is why we have the Tradeable interface.






