package edu.monash.fit2099.game.grounds;


import edu.monash.fit2099.game.items.HealthWater;

import java.util.ArrayList;

public class HealthFountain extends MagicalFountain {

    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H');
        this.fountainWater =new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new HealthWater() );
        }
    }


    @Override
    public void fillFountain() {
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWater.add(new HealthWater() );
        }
    }
}
