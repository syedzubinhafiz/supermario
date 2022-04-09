#### REQ 6 CLASS RESPONSIBILITIES 

Note: Wallet, TradeAction, Coin, AddToWalletAction, Toad, TradeableItem classes that are included in REQ6's class diagram are the same as in REQ 5.
Thus, for REQ6 we will include only the other newly modified/added classes for REQ6 in this repsonsiblities list.
Note that in the UML Class diagram for REQ6 that the red highlighted lines and classes represent the modified/added classes for REQ6 specifically.
Classes that are black represent classes existing in the basecode.
Classes that have other colours (other than black & yellow) are classes added/modifed for OTHER REQs.


**Player**
1. Attributes:
    new attribute -> ActionList additionalActions;
2. Constructor:
    Modify constructor to:
    
    - instantiate ActionList for additionalActions attribute.
    
    - instantiate TalkWithToadAction & add this action to the additionalActions ActionList.
    
3. In playturn() method:
    Modify to add additionalActions list to the passed in ActionList.
    
4. Methods:
   hasWrench() to return true if player has wrench in inventory, else return false.
        
**Toad**
1. Attributes:
    private static String[] sentences = [1st sentence, 2nd sentence, 3rd sentence, 4th sentence];

2. Methods:
    getSentence(int Index) which returns the String sentence referenced by the index given;
  
  
**TalkWithToadAction**
1. Class Overall Responsibility:

    This class is used to represent a talking with a Toad Action. 
    Note that this class will only be used for talking with the Toad actor.

2. Relationship with other classes:
    
    Inherits from Action class (extends this class).

3. Attributes: 

    private Player player;
    
4. Constructor: 
    
    creates instance of TalkWithToadAction with the player attached to it.
 
5. Methods: 
    overrides Action's execute method
        
    in public execute() method:
    
        - check if Player has wrench.
        
        - check if Player has powerstar effect. (player.hasCapability(INVINCIBLE)).
        
          if player's inventory has wrench && powerstar effect not there: 
            get random sentence from toad's sentences array (2, 3, 4th sentences only);
          elif has wrench && powerstar effect is there: 
            get random sentence from toad's sentences array (3, 4th sentences only);
          elif powerstar effect is there:
            get random sentence from toad's sentences array (1, 3, 4th sentences only);
          else randomly pick any of sentence 1,2,3,4 from sentences array.
          
    overrides Action's getHotKey() method to return the specific hotkey, which is: "d".

**Utils**
1. Class Overall Responsibility:

    This class is used to provide utility methods which other classes can use.

2. Relationship with other classes:
    
    T

3. Attributes: 

    private Player player;
    
4. Constructor: 
    
    creates instance of TalkWithToadAction with the player attached to it.
 
5. Methods: 
    overrides Action's execute method
        
    in public execute() method:
    
        - check if Player has wrench.
        
        - check if Player has powerstar effect. (player.hasCapability(INVINCIBLE)).
        
          if player's inventory has wrench && powerstar effect not there: 
            get random sentence from toad's sentences array (2, 3, 4th sentences only);
          elif has wrench && powerstar effect is there: 
            get random sentence from toad's sentences array (3, 4th sentences only);
          elif powerstar effect is there:
            get random sentence from toad's sentences array (1, 3, 4th sentences only);
          else randomly pick any of sentence 1,2,3,4 from sentences array.
          
    overrides Action's getHotKey() method to return the specific hotkey, which is: "d".