package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.RefillBottleAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.items.HealthWater;
import edu.monash.fit2099.game.items.PowerWater;
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;

public class PowerFountain extends MagicalFountain {

    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
        this.fountainWaterList=new ArrayList<>();
        for (int i=0; i < FINAL_WATER_CAPACITY; i++) {
            fountainWaterList.add(new PowerWater() );
        }
    }

    public Water getWater() {
        Water newWater = new PowerWater();
        return newWater;
    }
}
