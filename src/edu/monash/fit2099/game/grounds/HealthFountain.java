package edu.monash.fit2099.game.grounds;


import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.items.HealthWater;
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;

public class HealthFountain extends MagicalFountain {

    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H');
        this.fountainWaterList=new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWaterList.add(new HealthWater() );
        }
    }


    @Override
    public void fillFountain() {
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWaterList.add(new HealthWater() );
        }
    }
}
