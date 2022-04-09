#### REQ 3 CLASS RESPONSIBILITIES

New classes added for REQ3:
Koopa, DestroyShellAction, Suicide/GoombaHandler, Wrench, Dormant behaviour, SuperMushroom

*note: remove suicide class, add Enemy interface


**Goomba**
1. Changes to class:

   Add logic to playTurn() method to use RandomBias from Utils class to perform 10% suicide
   possibility ( through removal from map ).

2. Relationship with other classes:

   Association with Suicide/GoombaHandler and BehaviourClasses ( Wander/Dormant )
   Dependency on ActionList class, DoNothingAction


**Koopa**
1. Class Overall Responsibility:

   This class implements a new enemy type that will attack the player actor and follow it around.
   It will implement a new dormant behaviour when knocked unconscious by the player and remain on
   the map until destroyed by a player with a wrench. Drops a mushroom when its shell is destroyed.
   Behaves similarly to Goomba besides dormant behaviour. Punches with 30 damage and 50% accuracy.

2. Relationship with other classes:

   Extends actor class
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
   carried out.

2. Relationship with other classes:

   Extends AttackAction

3. Attributes:

   Similar to AttackAction...
   an Actor that represents the target of the attack
   String to represent attack direction
   No need for random number attribute

4. Constructor:

   Takes input for target Actor and String for direction
   Assigns attributes to input.

5. Methods:

   Overrides execute() and removes Koopa actor from the map, no need to ensure actor is Koopa as Dormant behaviour
   currently only used by Koopas. No need to ensure player has wrench as it is done in playTurn(). Returns message telling player that Koopa shell has been hit with Wrench.
   Needs to create a SuperMushroom object on the map at the location of Koopa object.

   Overrides menuDescription() to return different message notifying player shell has been destroyed successfully,
   e.g "KAPLOW KOOPA SHELL HAS BEEN OBLITERATED!!".


**Suicide/GoombaHandler**
1. Class Overall Responsibility:

   Stores Goomba objects to maintain a safe amount of spawns so the game does not self-destruct.
   Gives future programmers options and tools to work with and tweak Goomba spawns in future.

2. Relationship with other classes:

   Association with Goomba class
   Association with Sprout class

3. Attributes:
   ArrayList or any form of storage to keep track of Goomba objects
   An int representing the max number of Goombas

4. Constructor:

   Initialises empty ArrayList of Goomba object and assigns it to attribute

5. Methods:

   A getter likely called getGoombaCount() to return size of the ArrayList
   Or a better option could be a boolean method returning True or False to see if Goomba objects can still
   be spawned on the map, by checking ArrayList size with MaxGoomba attribute. Sprout class can use this to
   check if Goombas can spawn.


**Wrench**
1. Class Overall Responsibility:

   New weapon for player class to use. Can be picked up and dropped and has 50 damage and 80% acccuracy.
   Used for destroying Koopa shells and can be used for basic attacks as well (AttackAction).

2. Relationship with other classes:

   Extends WeaponItem class
   Implements Tradable interface
   AttackAction has dependency on this class

3. Attributes:

   Integer attribute representing damage, should not be changeable value
   Integer attribute representing hitRate of weapon, should not be changeable value
   String attribute representing UI display verb for attacking with weapon, e.g "KRONK" or "BONK"

4. Constructor:

   Requires 5 input values. All representing name of weapon, display character of weapon, damage of weapon
   , attacking verb and hit rate of weapon.
   Will be required to use super(name, displayChar, True/False(weapon portability))

5. Methods:

   Overrides all methods from parent class
   damage(), returns damage dealt by weapon
   verb(), returns attacking verb
   chanceToHit(), returns hit rate of weapon


**DormantBehaviour**
1. Class Overall Responsibility:

   To be applied to Koopa objects that have been defeated by the player. Responsible for making sure
   defeated Koopa objects do not move carry out any actions or behaviour. They cannot move, attack, wander,
   follow or interact with anything.

2. Relationship with other classes:

   none

3. Attributes:

   none

4. Constructor:

   none

5. Methods:

   Overriden getAction() method that requires Koopa object and game map as input. Will check if Koopa is on map
   Returns null always so playTurn does not receive any actions for Koopa object.


**SuperMushroom**
1. Class Overall Responsibility:

   Increases maxHP of players by 50 and changes the display character from 'm' to 'M'. Gives player SuperMushroom
   status and allows them to freely jump until they receive any damage.

2. Relationship with other classes:

   Implements Tradeable and Consumable interfaces
   Association with ActionList class
   Association with CapabilitySet class

3. Attributes:

   Similar to items class...
   String representing name of item
   char representing displayChar of item '^'
   boolean representing portability of item
   ActionList object
   CapabilitySet object

4. Constructor: 

   Requires input for attributes relating to name, displayChar, and portability
   Assigns input to attributes and intialises empty ActionList

5. Methods:

   Similar to items class...


