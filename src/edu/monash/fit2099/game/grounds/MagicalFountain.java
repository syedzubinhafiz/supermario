package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.itemrelated.RefillBottleAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.ConsumableItem;
import edu.monash.fit2099.game.items.Water;

import java.util.ArrayList;

public abstract class MagicalFountain extends Ground {

    protected ArrayList<Water> fountainWater;
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
        if(fountainWater.isEmpty()) {
            // replenish in next 5 turns once empty
            rechargeTimer++;
            if(rechargeTimer==6) {
                // replenish
                this.fillFountain();
                //to reset for the next time the fountain goes empty
                rechargeTimer = 0;
            }
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions=new ActionList();
        if(actor.hasCapability(Status.HAS_BOTTLE) && !fountainWater.isEmpty() && location.getActor()==actor){
            actions.add(new RefillBottleAction(getWater(), this));
        }
        return actions;
    }

    public Water getWater() {
        if(!fountainWater.isEmpty()) {
            return fountainWater.get(0);
        }
        return null;
    }

    public ConsumableItem removeWater(){
        ConsumableItem water = getWater();
        if(water!=null) {
            fountainWater.remove(0);
        }
        return water;
    }

    //New method to fill fountain
    public abstract void fillFountain();

    public int getCapacity() { return fountainWater.size();}

    public int getMaxCapacity() { return FINAL_WATER_CAPACITY;}
}
