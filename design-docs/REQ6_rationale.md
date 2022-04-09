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
While this alternative accounts for extensibility, however it would cause Player to an association with behaviour class,
which is an added strong dependency. Thus, would go against the Reduce Dependency Principle.
Both designs are okay and do not break any object-oriented principles. 


Also, in terms of extensibility for other behaviours/actions player might have, we can go ahead and add a behaviours list in the future,
however of as now with the current requirement and actions player can do, this shouldn't be needed.
But, with our current design, we discard the alternative since it goes agains the Reduce Dependency Principle.
And in our design only TalkWithToadAction has an association to Player.

