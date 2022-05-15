package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.RefillBottleAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.items.PowerWater;
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;

public abstract class MagicalFountain extends Ground {

    protected ArrayList<Water> fountainWaterList;
    protected final int FINAL_WATER_CAPACITY=10;

    private int rechargeTimer = 0;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public MagicalFountain(char displayChar) {
        super(displayChar);
        this.addCapability(Status.FOUNTAIN);
    }

    //Implementation for Refilling water
    @Override
    public void tick(Location location) {
        if(fountainWaterList.isEmpty()) {
            // replenish in next 5 turns once empty
            rechargeTimer++;
            if(rechargeTimer==5) {
                // replenish
                for(int i=0;i<FINAL_WATER_CAPACITY; i++){
                    this.fillFountain();
                }
            }
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions=new ActionList();
        if(actor.hasCapability(Status.HAS_BOTTLE) && !fountainWaterList.isEmpty() && location.getActor()==actor){
            actions.add(new RefillBottleAction(getWater(), this));
        }
        return actions;
    }

    public Water getWater() {
        return fountainWaterList.get(0);
    }

    public void removeWater(){
        fountainWaterList.remove(0);
    }

    //New method to fill fountain
    public abstract void fillFountain();

    public int getCapacity() { return fountainWaterList.size();}

    public int getMaxCapacity() { return FINAL_WATER_CAPACITY;}
}
