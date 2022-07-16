package edu.monash.fit2099.game.grounds;


import edu.monash.fit2099.game.items.HealthWater;

import java.util.ArrayList;


/**
 * Health Fountain is a class that provides the allows players to fill their bottles with HealthWater objects that provide healing to actors upon consumption.
 * This class extends MagicalFountain to implement specific functionalities; the type of water it stores.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public class HealthFountain extends MagicalFountain {

    /**
     * Constructor.
     * Fills up the HealthFountain with HealthWater objects to maximum capacity upon instantiation.
     */
    public HealthFountain() {
        super('H', "Health Fountain");
        this.fountainWater =new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new HealthWater() );
        }
    }


    /**
     * This fill fountain method refills the HealthFountain to maximum capacity with HealthWater objects when called.
     */
    @Override
    public void fillFountain() {
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new HealthWater() );
        }
    }
}
