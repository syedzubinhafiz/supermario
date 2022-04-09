**Design Rationale**

1. Implementing Dormancy

For REQ3 we mainly had to implement a dormant effect for all Koopa classes that were knocked unconscious by the player. In order to do this, we had to first create a new Koopa class to represent the new enemy type we were going to introduce into the game.


Following what was done for the Goomba class we decided to extend the Actor class and keep the same behaviour attribute. Instead of using a HashMap this time, we elected to use TreeMaps instead. The process of selecting actions from behaviours in playTurn() is slightly random as the entries are not sorted, the way it functions currently gives the first action found to be not null. TreeMap allows us to have sorted entries in the Map, allowing us to give the Dormant Behaviour the highest priority and actually have priority selection function. This allows the unconscious Koopa's to always return dormant actions once labelled as Dormant.

Alternatively, using a for loop ranging from 0 to the size of the HashMap for indexing would have been a plausible method to loop through entries with priority. However, this would require us to have the keys entered from 1-10 ( highest to lowest priority ). This method as a whole is just more troublesome to code and would have likely made the performance of the game worse. Aside from that future programmers would also have had to been made aware of this to avoid any mistakes.


We plan to keep Koopa's from performing any unwated actions by returning null constantly when getActions() of the new DormantBehaviour class is called. This is how the playTurn() method of the Koopa class is used to get actions every tick. The if statement in the AttackAction class will be changed to check if the target is unconscious and not a Koopa in order to avoid removing them from the map. An else statement to change the display character of the Koopa to 'D' and to add the Dormant Status to the Koopa will be added as well.

The Dormant status will be used in allowableActions(). The code checks the target of attack for Dormant Status with hasCapability() and gives the player the option to use the new DestroyShellAction class on it if the Dormant Status is present. The old code will have to be updated to check for HOSTILE_TO_ENEMY status and not Dormant in the target actor to perform an attack. This follows the requirement which states that players cannot be given the option to attack dormant Koopas.

The new DestroyShellAction class will be made to extend the AttackAction. Because destroying a shell is a type of a attack action, it is fitting to extend the AttackAction since both of them behave similarly (aligns with the meaning of object-oriented) .

2. Consuming Items



