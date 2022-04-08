Tree Class


1. Class Overall Responsibilities: This is an existing class where we implement the different growth cycle stages from a sprout to a mature tree. Additionally, certain functions take place during the growth which is also accounted for, within the methods.
1. Relationship With Other Classes: Has dependencies with Ground, Coin, Goomba, and Koopa
1. Attributes:
1. private int turnCounter;
1. private TreeCycleStage stage

Setter/s:

1. setStage();

TreeCycleStage enum class :

1. Has SPROUT, SAPLING & MATURE attributes
1. Extends the Tree class

\4.  Constructor:

1. Modify the constructor so as to display a character “+” when a Tree object is instantiated within the sproutTick() method; “t” is displayed when saplingTick() method is called and finally “T” is called when matureTick() method is called.


\5. Overridden method/s:

Override the ground.tick() method so that it  is as follows:

ground.tick(Location location) {

if(stage = SPROUT) { sproutTick() }

Elif (SAPLING) { saplingTick() }

Esle { matureTick() }

}


\6. Methods:

1. sproutTick() method:
1) Use a random bias generator to calculate the probability. If randomBias =0.1,

Spawn a Goomba. We first get the location of the ground the argument given in the tick() function). Then, Location.addActor(new Goomba());

1) We also need to check if an actor stands on it. So we call the ground.tick() method to check the current location for an actor object, the Goomba spawns else, nothing happens.
1) A turnCounter will keep iterating every time this method is called.Once turnCounter reaches a value of 10,setDisplayChar(“t”); & setStage() to set stage attribute of Tree to be a SAPLING.Reset the turnCounter to 0 before exiting the method.

\2. saplingTick() method:

1) Use the random bias generator again, and if randomBias=0.1, a Coin object is instantiated. The coin is dropped on the current location of the sapling which can be found using the location argument within the groundTick() method.
1) Once turnCounter reaches a value of 10, setDisplayChar(“T”); & setStage() to set stage attribute of Tree to be MATURE.Reset the turnCounter to 0 before exiting the method.


\3. matureTick() method:

1) Use the random bias generator, if randomBias=0.15, we spawn a Koopa object on the current location of the actor using the ground.Tick() method.Then,Location.addActor(new Koopa());
1) Now, check for ‘Dirt’ object in the surrounding.Have list of dirtDestinations in Mature for exitDestinations that have a dirt ground.

Find dirtDestinations:

* Location of mature.getExits() -> returns exits, for each exit-> exit.getDestination(),getGround() to check if the ground is a dirt. If yes, add location to dirtDestinations
1) If turnCounter%5==0 && dirtDestination is true, we spawn a sprout using the      setDisplayChar() method.
1) Again, if randomBias<=0.20, get location of ground using ground.Tick() method and then, Location.setGround(new Dirt());

Meaning the sprout has withered and died.
