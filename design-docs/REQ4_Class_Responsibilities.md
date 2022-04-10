#### REQ 4 CLASS RESPONSIBILITIES

New classes for REQ4:
SuperMushroom, ConsumeAction, PowerStar, InstaKillAction, Consumable interface, HigherGround interface

**SuperMushroom**
1. Class Overall Responsibility:

   To give player ability to jump freely and increase max health points.
   Buff lasts until damage of any form is taken. Needs to change display character
   of player. Execute methods of any actions that damage player actor have to be
   changed to check for TALL status.

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

**ConsumeAction**
1. Class Overall Responsibility:

   Responsible for providing player with status/buffs in relation to consuming any consumables.
   Has to change hp values, display characters and update capabilities of player by assigning
   new Statuses.

2. Relationship with other classes:

   Extends actions class

3. Attributes:

   Actor object to store target of consumption (player)
   Item object to store item being consumed

4. Constructor:

   Takes input for target Actor and item object
   Assigns attributes to input.

5. Methods:

   Overridden execute() method which requires item object being consumed and player object.
   Checks what item is being consumed and proceeds with making respective changes based on what item is consumed.
   Also has to provide player with required Statuses, either TALL or INVINCIBLE.
   Has to remove item from player inventory after consumption if item is SuperMushroom.

   Display method to notify user item has been consumed

**PowerStar**
1. Class Overall Responsibility

   To give player ability to walk freely and heal player by 200 health points.
   Buff lasts 10 ticks in game. Needs to make player invincible to damage. Execute methods of any actions that
   damage player actor have to be changed to check for INVINCIBLE status.
   Allows player to use InstaKillAction when having status and walk over walls and trees.
   Has to convert walls and trees into dirt and drop a coin ($5)

2. Relationship with other classes:

   Implements ConsumableItem interface
   Extends item class

3. Attributes:

   Similar to items...
   Boolean representing consumption of item
   Int representing numberOfTicks
   Int representing hp increase value
   String representing name of item
   char representing displayChar of item '^'
   boolean representing portability of item
   ActionList object
   CapabilitySet object

4. Constructor:

   super( name, displayChar, portable )
   assigns hp heal value attribute 200
   isConsumed assigned false

5. Methods:

   allowableActions() returns to player ConsumeAction instance
   Getters for name, hpIncrease attribute, displayChar
   Override item.tick() to increment numberOfTicks by 1 each time it is called.
   Before incrementing, should check if boolean attribute representing consumption is true or false
   If true, set numberOfTicks to 0
   Need to remove item when numberOfTicks reaches 10
   String Display methods for when item is active on player

**InstaKillAction**
1. Class Overall Responsibility:

   Class provides player with the ability to instantly kill any enemy it comes across on the map
   when actively having the INVINCIBLE status.

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

   Overrides execute() and removes target actor from the map.
   Returns message telling player that target actor has been killed

   Overrides menuDescription() to return different message notifying player shell has been destroyed successfully,
   e.g "OBLITERATED TARGET_ACTOR_NAME!!".



**Consumable interface**
getConsumeAction(), boolean attribute isConsumed

isConsumed returns True or False depending on whether item is consumed

Allows items to implement and use all methods within it.
Provides Magical items with a getAction method so the player can successfully consume an item.

For future extensibility of program.

Implementation in PowerStar and SuperMushroom would require the implementation of the getAction method by...

Taking an Actor, the player and an Item, the magical item as input and returns to the player
the ConsumeAction.

**HigherGround interface**
convertToDirt()

Allows higher ground(Wall and Tree currently), Ground types to use methods within it.
Provides all types of higher ground with a convertToDirt() method that changes the ground to dirt and drops a coin ($5).

For future extensibility of program.


