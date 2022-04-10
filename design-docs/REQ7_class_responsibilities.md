#### REQ 7 CLASS RESPONSIBILITIES 

Player class has added attributes and methods.
ResetGameAction is a new class.

**Player**
1. Attributes:
   resetStatus boolean, true if game has been reset, false if not.
2. Methods:
   resetPlayerStatus, to reset the player's status & remove any effect from SuperMushroom and PowerStar
        

**ResetGameAction**
1. Class Overall Responsibility:

    This class is used to represent the act of Player resetting the game.
    This will be added to the player's actions list if the player's resetStatus is false.

2. Relationship with other classes:
    
    Dependencies on several classes (Enemy, Tree, Dirt, Player, Utils, Coin)

3. Attributes: 

    None
    
4. Constructor: 
    
    creates instance of ResetGameAction.
 
5. Methods: 
    overrides Action's execute method
        
    in public execute() method:
    
        - resets the Player's HP
        - resets the Player's status
        - get the width and height of the Gamemap map argument.
        - loop through width and height to get each location in the map.
        - for each location:
            if there is a Tree, convert it to Dirt based on a 50% chance
            if there is an Enemy, remove it from the map (kill it)
            for all coins, remove it
          
    overrides Action's getHotKey() method to return the specific hotkey, which is: "r".
