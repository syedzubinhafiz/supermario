#### Design Rationale

**1: Purchasing an item: TradeAction and Wallet class**

tradeAction extends Action. This allows for the action to purchase an item to be done. 
We want tradeAction to inherit from Action, so that there is an "is-a" relationship that would allow for reusability of code & extensibility of tradeAction class.


Wallet extends Item. By inheriting the abstract Item class, it has the necessary methods to provide add actions (such as tradeAction) 
to the wallet item. This would allow an actor to use their Wallet item to perform this tradeAction action.


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

**2: Coin and Wallet**
Here, wallet is added to the player's inventory as a non-portable item. Whereas coin is added to the Wallet of the player rather than added to
the player's inventory.

