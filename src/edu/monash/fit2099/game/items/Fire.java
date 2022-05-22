package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.grounds.Dirt;
/**
 * Fire class represents the Fire dropped on the ground due to Mario's FireAttackAction
 * @author: Syed Zubin Hafiz
 * @version: 1.0.0
 * @see edu.monash.fit2099.game.items
 */
public class Fire extends Item {
    /**
     * A private attribute turnCounter which keeps track of turns played
     */
    private int turnCounter;
    private GameMap map;

    /**
     *Constructor
     * @params name of the item
     * @params displayChar of Fire which is to be shown on the game map
     * @params portable to see if the item is transferable
     */
    public Fire(GameMap map) {
        super("Fire", 'v', false);
        this.map = map;
    }


    /**
     * This method is responsible to deal damage to the player due to the Fire dropped on the ground during Mario's fire
     * attack action effect
     * @param location The location of the ground on which we lie.
     */
    public void tick(Location location) {
        turnCounter++;
        if (turnCounter <= 3) {
            if (location.getActor() != null) {
                Actor actor = location.getActor();
                actor.hurt(20);
                Display d = new Display();
                d.println(location.getActor()+" is being hurt by fire!");
                if(!actor.isConscious() && !actor.hasCapability(Status.DORMANT)){
                    map.removeActor(actor);
                }
            }
        }
        else {
            location.removeItem(this);
        }
    }
}
