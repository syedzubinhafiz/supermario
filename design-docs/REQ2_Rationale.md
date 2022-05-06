### Jump Action Class
For this requirement, we have decided to create a new class called Jump Action, and inherit the Action abstract class. This makes sense because the jump action is an addition to all the possible actions the edu.monash.fit2099.game provides that a player can perform. To make the appropriate things work for this requirement we need to make changes to a couple of other classes, namely: Wall & Tree. More specifically, we came up with a new interface called ‘HigherGround’ which provides an empty method called getJumpAction().The Wall and Tree classes implement this interface since for the current implementation, these two objects are considered as higher ground.

In the Wall class, we set the final attributes of success rate and damage referring to the that these values do not change and then override the allowable actions list.


We set final attributes within the class and set a precondition while overriding the method so that it doesn’t affect the Single Responsibility Principle which clearly states that every module, class, or function in a computer program should have responsibility for a single part of that program's functionality, and it should encapsulate that part. Hence, the Utils class which generates our probability bias will not be responsible to handle all the success rates ranging from growing sprouts to completing jumps. Similarly, we also decided to set the final success rate and damage values for every growth stage object so we can compare them to the random bias, and if only they match, the jump functionality will take place.

Additionally, if we were to add new high ground objects we can have the object’s classes implement the HigherGround interface and,call the getJumpAction in that particular object's class and override the allowable actions list, the same way we did previously (given it also extends from Ground) without actually having to make any modifications to the Jump Action class specifically. This goes to show, that the class adheres to the Open-Closed Principle.


