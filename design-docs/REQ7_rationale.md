#### REQ7 Design Rationale

**1: ResetGameAction**

To be able to reset the game, the player needs to have an option to reset the game.
Thus, we have the ResetGameAction that extends the abstract Action class.

Note that currently, ResetGameAction has dependencies to a few classes (Tree, Utils, Dirt, Player, Coin, Enemy).
Thus, this may not be optimal, however we chose this approach due to these 2 benefits:
1. Straightforward approach.
For the execute() method to check for all trees and all locations, it needs to iterate through the map's width and height to get all the locations
in the map. Then, for each location we will check for the Trees, the Enemys and the Coins in each location to perform the require
resetting for each of them if they exist at the location. 
Thus, note that it is straightforward if the execute() method does this.
2. Single Responsibility Principle.
Currently, we want the resetting of the game to be done by a single class, and therefore, we have the ResetGameAction class that
is added to the player's actions list if the player has not yet reset the game. Thus, we believe having the checking and dependencies
within the ResetGameAction allows for the responsibility to be completed in a single class.

Therefore, due to the rationale above, we believe that the introduced dependencies make the implementation straightforward and supports
the single responsibility principle and thus we decided to go with this implementation.



//NEW IMPLEMENTATION

Will be using ResetManager class, the class will store all other classes needed to be
reset in a private attribute resettableList.

ResetManager will have associations with classes it stores in ArrayList

All classes that need to be reset will have to implement resettable. Will likely have to implement registerInstance in constructor
of class. What this does is adds the instance of the class to the resetmanager singleton class' list.

ResetManager will be Singleton so that it keeps track of all resettable items without having to be too complicated,
won't have to look through different ResetManager classes

ResetManager run method will make all changes by going through the resettableList, each resettable item have to have implemented resetInstance()
as a method to make whatever changes needed. This method is called on all item in resettableList
