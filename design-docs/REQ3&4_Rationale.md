**Design Rationale**

1. Implementing Dormancy

For REQ3 we mainly had to implement a dormant effect for all Koopa classes that were knocked unconscious by the player. In order to do this
, we had to first create a new Koopa class to represent the new enemy type we were going to introduce into the game.


Following what was done for the Goomba class we decided to extend the Actor class and keep the same behaviour attribute. Instead of using a HashMap this time
, we elected to use TreeMaps instead. The process of selecting actions from behaviours in playTurn() is slightly random as the entries are not sorted,
the way it functions currently gives the first action found to be not null. TreeMap allows us to have sorted entries in the Map, allowing us to give the Dormant Behaviour
the highest priority and actually have priority selection function. This allows the unconscious Koopa's to always return dormant actions once labelled as Dormant.

Alternatively, using a for loop ranging from 0 to the size of the HashMap for indexing would have been a plausible method to loop through entries with priority.
However, this would require us to have the keys entered from 1-10 ( highest to lowest priority ). This method as a whole is just more troublesome to code and would have
likely made the performance of the game worse. Aside from that future programmers would also have had to been made aware of this to avoid any mistakes.


We plan to keep Koopa's from performing any unwanted actions by returning null constantly when getActions() of the new DormantBehaviour class is called.
This is how the playTurn() method of the Koopa class is used to get actions every tick. The if statement in the AttackAction class will be changed to check if the target is
unconscious and not a Koopa in order to avoid removing them from the map. An else statement to change the display character of the Koopa to 'D' and to add the Dormant Status
to the Koopa will be added as well.

The Dormant status will be used in allowableActions(). The code checks the target of attack for Dormant Status with hasCapability() 
and gives the player the option to use the new DestroyShellAction class on it if the Dormant Status is present. The old code will have to be updated to check for
HOSTILE_TO_ENEMY status and not Dormant in the target actor to perform an attack. This follows the requirement which states that players cannot be given the option to attack dormant Koopas.

The new DestroyShellAction class will be made to extend the AttackAction. Because destroying a shell is a type of a attack action
, it is fitting to extend the AttackAction since both of them behave similarly (aligns with the meaning of object-oriented) .

This implementation of DestroyShellAction and DormantBehaviour allows us to code with the Single Responsibility Principle
as we delegate the functionality of Koopa class' actions to other classes, instead of having Koopa be responsible for
all of it's actions.

We also plan to implement an Enemies interface to allow for future extensibility. We have so far implemented a getAttackAction() method which simply returns an AttackAction to the Goomba and Koopa classes.
We believe adding the interface will allow us to add more functionality to Koopas and Goombas; such as the ability to attack the Player entity.

2. Consuming Items

We have added 2 new classes called PowerStar and SuperMushroom, both of which extend Item class and Implement a newly created
Consumable class. We plan to implement Magical Items by giving the player the option to consume them whenever present in their inventory.

For the act of consuming a magical item, our team has elected to create a separate ConsumeAction class
that is responsible for providing the player with any health and behavioural
changes. This form of implementation adheres to the Single Responsibility Principle as we once again delegate the
functionality of consuming PowerStar or SuperMushroom to another class.

We also plan to implement a Consumable interface with a getAction method that returns ConsumeAction for the player to use.
This implementation of the Consumable interface will allow us to code with the Open-Closed Principle. This is because
any new consumable items added to the game after the initial implementation of the action in the player class
will be able to be consumed without having to modify any code. You would only have to add the item itself. 
This ensures that the actions will be made available to the players whenever a magical item is present in their inventory.

Without the interface, we would have had to check for the individual items in the inventory to see if they were instances of SuperMushroom or PowerStar.
With the Consumable interface, we can just check if items are an instance of the interface.

Since this implementation aligns with two principles of design, we have deemed it to be good design for our game code.


For the actual consumption of items, we decided on using a separate ConsumeAction class that extends Actions.
This class will take as input the Magical Item and the Player object. It overrides the execute method to perform any changes to
the health of the player and gives the player Statuses that will allow them to carry out any additional actions, like freely jumping, destroying ground or instantly killing enemies.



After the execute method is completed, the item will be removed from the players inventory.


The act of consuming Magical Items provides players with a new action.
InstaKillAction. The player will also be able to jump freely over walls and trees as well as destroy them
if they consumed a PowerStar.

This is implemented by having checks in the Goomba and Koopa classes allowableActions() method. The if statement
will check the player capability set to see if it contains INVINCIBLE and returns an ActionList instance with InstaKillAction if present.

The ability to destroy ground is currently implemented by having Wall and Tree detect when a player has INVINCIBLE status in its
allowableActions() method. It then changes itself to Dirt and drops a Coin ($5) with a convertToDirt() method from HigherGround interface.

Alternatively, we tried implementing a new method in both Wall and Tree called convertToDirt() but decided that implementing 
an interface would be much better for future extensibility.

Another alternative we tried was implementing a DestroyGroundAction to follow the Single Responsibility Principle 
but doing so would require the actor to perform the action. Using allowableActions() also causes the DestroyGroundAction
to be shown in the player's menu as an option. 

