#### REQ 3 CLASS RESPONSIBILITIES

New classes added for REQ3:
Koopa, DestroyShellAction, Wrench, Dormant behaviour, SuperMushroom, Enemy interface




**Goomba**
1. Changes to class:

    Add logic to playTurn() method to use RandomBias from Utils class to perform 10% suicide
    possibility ( through removal from map ).

    Has new attribute to count whenever Goomba is added or removed from map 
    Has new attribute called Max_Goomba_Limit, final number limit for Goomba spawns

2. Relationship with other classes:

    Implements Enemy interface
    Association with Suicide/GoombaHandler and BehaviourClasses ( Wander/Dormant )
    Dependency on ActionList class, DoNothingAction

3. Methods:

    Boolean method canSpawn returns true or false based on GoombaCount and Max_Goomba_Limit

    Getters for new attributes

**Koopa**
1. Class Overall Responsibility:

   This class implements a new enemy type that will attack the player actor and follow it around.
   It will implement a new dormant behaviour when knocked unconscious by the player and remain on
   the map until destroyed by a player with a wrench. Drops a mushroom when its shell is destroyed.
   Behaves similarly to Goomba besides dormant behaviour. Punches with 30 damage and 50% accuracy.

2. Relationship with other classes:

    Extends actor class
    Implements Enemy interface
    Association with Behaviours and BehaviourClasses ( Wander/Dormant )
    Dependency on ActionList class, DoNothingAction, DestroyShellAction

3. Attributes:

    TreeMap of Behaviours

4. Constructor:

    Takes no input but uses super("Koopa", "K", 100) and puts WanderBehaviour
    into the TreeMap with priority (Integer) as 10.

5. Methods:

    Has its own implementation of playTurn method with added checks to see if TreeMap for behaviours
    contains Dormant behaviour, returns new DoNothingAction() whenever Dormant behaviour is found.

    Overrides allowableActions() and implements its own version, which checks if player has
    a Wrench in their inventory, adds the newly created DestroyShellAction to ActionList instance


**DestroyShellAction**
1. Class Overall Responsibility:

    This class provides the player with a new action to destroy any dormant Koopa objects on the map.
    The action requires a wrench and has 100% accuracy. Drops a SuperMushroom object when action successfully
    carried out. Also stores damage, hit rate of the wrench and verb of the wrench.

2. Relationship with other classes:

    Extends AttackAction

3. Attributes:

    Similar to AttackAction...
    an Actor that represents the target of the attack
    String to represent attack direction
    No need for random number attribute
    Attribute representing wrench object

5. Constructor:

    Takes input for target Actor and String for direction
    Assigns attributes to input.

6. Methods:

    Overrides execute() and removes Koopa actor from the map, no need to ensure actor is Koopa as Dormant behaviour
    currently only used by Koopas. No need to ensure player has wrench as it is done in playTurn(). Returns message telling player that Koopa shell has been hit with Wrench.
    Needs to create a SuperMushroom object on the map at the location of Koopa object.

   Overrides menuDescription() to return different message notifying player shell has been destroyed successfully,
   e.g "KAPLOW KOOPA SHELL HAS BEEN OBLITERATED!!".



**Wrench**
1. Class Overall Responsibility:

   New weapon for player class to use. Can be picked up and dropped and has 50 damage and 80% acccuracy.
   Used for destroying Koopa shells and can be used for basic attacks as well (AttackAction).

3. Relationship with other classes:

   Extends WeaponItem class
   Implements Tradable interface
   AttackAction has dependency on this class

4. Attributes:

   Integer attribute representing damage, should not be changeable value
   Integer attribute representing hitRate of weapon, should not be changeable value
   String attribute representing UI display verb for attacking with weapon, e.g "KRONK" or "BONK"

5. Constructor:

   Requires 5 input values. All representing name of weapon, display character of weapon, damage of weapon
   , attacking verb and hit rate of weapon.
   Will be required to use super(name, displayChar, True/False(weapon portability))

6. Methods:

   Overrides all methods from parent class
   damage(), returns damage dealt by weapon
   verb(), returns attacking verb
   chanceToHit(), returns hit rate of weapon


**DormantBehaviour**
1. Class Overall Responsibility:

   To be applied to Koopa objects that have been defeated by the player. Responsible for making sure
   defeated Koopa objects do not move carry out any actions or behaviour. They cannot move, attack, wander,
   follow or interact with anything. To be added to Koopa's behaviours.

2. Relationship with other classes:

   Implements Behaviour

3. Attributes:

   none

4. Constructor:

   none

5. Methods:

   Overridden getAction() method that requires Koopa object and game map as input. Will check if Koopa is on map.
   Returns null always so playTurn does not receive any actions for Koopa object.


**SuperMushroom**
1. Class Overall Responsibility:

   To give player ability to jump freely and increase max health points.
   Buff lasts until damage of any form is taken. Needs to change display character
   of player. Execute methods of any actions that damage player actor have to be
   changed to check for TALL status. ( For this requirement it has to drop when Koopa Shell is destroyed )

2. Relationship with other classes:

   Implements ConsumableItem interface
   Extends item class

3. Attributes:

   Similar to items...
   Int representing hp increase value
   Boolean representing consumption of item
   String representing name of item
   char representing displayChar of item '^'
   boolean representing portability of item
   ActionList object
   CapabilitySet object

4. Constructor:

   super( name, displayChar, portable )
   assigns hp increase value attribute 50

5. Methods:

   allowableActions() returns to player ConsumeAction instance
   Getters for name, hpIncrease attribute, displayChar
   two tick() methods for when on ground or actor inventory

**Enemy Interface**
getAttackAction()

Allows enemy Actors to implement and use all methods within it.
Provides enemy actors with a getAction method to so the player can attack it. 
For future extensibility, can provide actions for attacking players in future.

Implementation in PowerStar and SuperMushroom would require the implementation of the getAction method by...

Taking an Actor, the player and an Item, the magical item as input and returns to the player
the ConsumeAction.




