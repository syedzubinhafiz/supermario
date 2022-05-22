package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.game.items.PowerWater;

import java.util.ArrayList;

/**
 * Power Fountain is a class that provides the allows players to fill their bottles with PowerWater objects that provide actors with an increase to their base damage.
 * This class extends MagicalFountain to implement specific functionalities; the type of water it stores.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public class PowerFountain extends MagicalFountain {

    /**
     * Constructor.
     * Fills up the PowerFountain with PowerWater objects to maximum capacity upon instantiation.
     */
    public PowerFountain() {
        super('A', "Power Fountain");
        this.fountainWater =new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new PowerWater() );
        }
    }


    /**
     * This fill fountain method refills the PowerFountain to maximum capacity with PowerWater objects when called.
     */
    @Override
    public void fillFountain() {
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new PowerWater());
        }
    }
}

