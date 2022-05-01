package game.grounds;


import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.items.Coin;

public class Sapling extends Tree{


    /**
     * Constructor for Sapling stage
     * Overrides the constructor for tree to change the display char for Sapling and sets the appropriate success rate,
     * damage and name for this particular Tree stage
     */
    public Sapling() {
        super('t', 0.8, 20, "Sapling");
    }


    @Override
    /**
     * Spawns a coin of value '20' if the attained randomBias agrees with the probability. After 10 turns the sapling
     * turns into a mature tree.
     * @param Location of the ground
     */
    public void tick(Location location){
        super.tick(location);
        turnCounter++;
        if (Utils.getRandomBias() <= 0.1) {
            location.addItem(new Coin(20));
        }
        if (turnCounter == 10) {
            location.setGround(new Mature());
        }
    }

}

