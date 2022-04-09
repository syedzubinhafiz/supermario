#### Design Rationale

**1: TalkWithToadAction**

To be able to interact with Toad, the player needs to have an option to talk with it.
Thus, we have the TalkWithToadAction that extends the abstract Action class.

In this game, the ideal way to interact with the object is by attaching an appropriate action to its corresponding object 
(aligns with the meaning of "object-oriented").
Thus, this is shown with this: **Player ---<<uses>>---> Action** (dependency, not shown in the class diagram for REQ6 as it was already 
existing in the basecode), and **TalkWithToadAction ---<<interacts with>>---> Player** (association). 
Here we are adding an instance of TalkWithToadAction to the actionsList via the playTurn method in the Player class.
This allows the Player's actions shown in the menu to have an action to talk with Toad.

Alternatively, we can have a behaviours hashmap, and have a TalkToadBehaviour class that is added to the behaviours of the player.
And in playTurn method can go through the behaviours of the player to get the action from that behaviour for the player.
While this alternative accounts for extensibility, however it would cause Player to have an association with behaviour class,
which is an added strong dependency for the Player class. Thus, would go against the "Reduce Dependency Principle."
Note that both designs are alright and do not break any object-oriented principles. 

Thus, with our current design, we discard the alternative since it goes against the Reduce Dependency Principle, and also
because we should not need a list of behaviours for the player to choose from (not in the requirements).
So, in our design only TalkWithToadAction has an association to Player and we will go with this implementation.



