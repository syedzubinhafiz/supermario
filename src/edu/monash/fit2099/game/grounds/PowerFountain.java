package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.game.items.PowerWater;

import java.util.ArrayList;

public class PowerFountain extends MagicalFountain {

    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
        this.fountainWater =new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new PowerWater() );
        }
    }

    @Override
    public void fillFountain() {
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new PowerWater());
        }
    }
}
