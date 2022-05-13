package edu.monash.fit2099.game.grounds;


import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.Utils;
import edu.monash.fit2099.game.actors.FlyingKoopa;
import edu.monash.fit2099.game.actors.Koopa;
import edu.monash.fit2099.game.items.FireFlower;

import java.util.ArrayList;


/**
 * The Sprout class represents the first third and final cycle stage and handles all the functionalities,the actor has with mature
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public class Mature extends Tree {

    /**
     * Constructor for the Mature stage
     * Overrides the constructor for tree to change the display char for Mature and sets the appropriate success rate,
     *damage and name for this particular Tree stage
     */
    public Mature() {
        super('T', 0.7, 30,  "Mature");
    }


    @Override
    /**
     * Overrides Ground's tick() method
     * Spawns a Koopa if the randomBias attained agrees with the probability and if there are no other actors in the current
     * location.
     * After every 5 turns played, a sprout is spawned in either of the four directions given it has 'dirt'
     *
     *
     * @param location Location of the ground
     */
    public void tick(Location location){
        super.tick(location);
        if (Utils.getRandomBias() <= 0.15 && !location.containsAnActor()) {
            if(Utils.getRandomBias() <= 0.5) {
                location.addActor(new Koopa());
            }else {
                location.addActor(new FlyingKoopa());
            }

        }
        if (Utils.getRandomBias()<=0.5){
            location.addItem(new FireFlower());
        }
        if (turnCounter % 5 == 0) {
            // make an empty list of all surrounding exits that have fertile ground in the edu.monash.fit2099.game map
            ArrayList<Exit> matureExits = new ArrayList<Exit>();
            for (Exit exit : location.getExits()){
                if (exit.getDestination().getGround().getDisplayChar() == '.') {
                        matureExits.add(exit);
                }
            }
            ArrayList<Integer> indexes = new ArrayList();
            for (int i=0;i < matureExits.size();i++){
                indexes.add(i);
            }
            if ((matureExits.size() > 0 && indexes.size() > 0)) {
                matureExits.get(Utils.getRandomFrom(indexes)).getDestination().setGround(new Sprout());
            }
        }
        if (Utils.getRandomBias() <= 0.2) {
            location.setGround(new Dirt());
        }
    }
}
