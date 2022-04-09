#REQ2 Class Responsibilities

##Jump Action Class


#####1. Class Overall Responsibilities: 
We add a new class called JumpAction which extends the Player class. The responsibility of this class is to allow the actor to jump whenever it is in front of higher ground. We also account for the success rate of jumps across various objects on which the character can jump on.




#####2. Relationship With Other Classes:
Inherits from Action since it is an additional action the player can perform.
#####3. Constructor: 
In this constructor, the damage, location and success rate for all the objects,i.e when Wall, Sprout, Sapling and Mature is passed as the actor, in their corresponding classes/methods.
#####4. Attributes:

    Location location

    int damage

    Utils utils=new Utils();

#####5. Methods:

(execute(Actor actor, Gamemap map) {};

If no Super Mushroom is consumed (checks actor's status on whether they have consumed it), then based on JumpAction’s success rate, which we will determine using the randomBias within the Utils class,

we let the player go to jump over to the high ground. Otherwise, we implement the player.hurt(damage) method and deal corresponding damage to the player once they fail the jump.

If a  Super Mushroom is consumed (checks actor's status on whether they have consumed it):

Let the player go to jumped over location straight away and print a line on the console based on the outcome.


#####Additional Actions:

In the Wall class: Override ground’s allowableActions(Location location) method to also add a new JumpAction(Location location, damage, SUCCESS_RATE) to each of these IF the player has capability MUST_JUMP & doesn’t have POWERSTAR capability & if (actor at location != current actor)

In the Tree class: We again override the ground’s allowableActions() method within every single ‘tick’ method but change the pre-condition before executing the jumpActions’s execute method that corresponds to sprout,sapling and mature’s success rate.

##Player class & Status enum class:
We modify Status enum class to include enum value MUST_JUMP, which signifies that the actor cannot walk over certain tall objects.
Thus, in player class's constructor, we add MUST_JUMP to its capabilitySet. (This MUST_JUMP will be checked for in JumpAction class)
