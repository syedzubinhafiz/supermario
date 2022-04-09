#### REQ 4 CLASS RESPONSIBILITIES

New classes for REQ4:
SuperMushroom, ConsumeAction, PowerStar, InstaKillAction, DestroyGroundAction, ConsumableItem

**SuperMushroom**
1. Class Overall Responsibility:

   To give player ability to jump freely and increase max health points.
   Buff lasts until damage of any form is taken. Needs to change display character
   of player. Execute methods of any actions that damage player actor have to be
   changed to check for TALL/SUPERMUSHROOM status.

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
   Has to remove item from player inventory after consumption.

   Display method to notify user item has been consumed

**PowerStar**
1. Class Overall Responsibility

   To give player ability to walk freely and heal player by 200 health points.
   Buff lasts 10 ticks in game. Needs to make player invincible to damage. Execute methods of any actions that
   damage player actor have to be changed to check for STARBUFF/INVINCIBLE status.
   Allows player to use DestroyGroundAction and InstaKillAction when having status

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

**DestroyGroundAction**
1. Class Overall Responsibility:

   Class provides player with the ability to walk freely on the map and destroy
   all Trees and Walls the player may come across when actively having the INVINCIBLE status
   Should not allow any JumpAction to occur. Allows actor to move into Wall or Tree, destroys it and
   changes it to dirt as well as drops $5 coin at the location.

2. Relationship with other classes:

   Extends MoveActorAction

3. Attributes:

   Location object to represent target location
   String representing direction
   String representing the command key/ hot key

4. Constructor:

   Follows MoveActorActions constructor, has to use super()
   Requires Location object to use as a location to move to, requires String representing direction

5. Methods:

   Overrides execute() method to convert Floor or Tree type into Dirt, then
   move player into that location as well as create a new coin object worth $5 at the location.
   Execute should take Actor object and GameMap object
   Need to override allowableActions() method in ground to check if
   ( actor at location != current actor ), this makes sure it isn't going to its current
   position and player is checked to see if it has INVICIBLE status with hasCapability().
  


**Consumable interface**
getConsumeAction()


