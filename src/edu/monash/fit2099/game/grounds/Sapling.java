package edu.monash.fit2099.game.grounds;


import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.Utils;
import edu.monash.fit2099.game.items.Coin;

/**
 * The Sapling class represents the second tree cycle stage and handles all the functionalities,the actor has with sapling
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.grounds
 */
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
        if (Utils.getRandomBias() <= 0.1) {
            location.addItem(new Coin(20));
        }
        if(Utils.getRandomBias()<=0.5){
            location.addItem(new FireFlower());
        }
        if (turnCounter == 10) {
            location.setGround(new Mature());
        }
    }

}

