package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.grounds.Dirt;

public class Fire extends Item {

    int turnCounter;

    /**
     *
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    public void tick(Location location) {
        turnCounter++;
        if (turnCounter <= 3) {
            if (location.getActor() != null) {
                location.getActor().hurt(20);
                Display d = new Display();
                d.println(location.getActor()+" is being hurt by fire!");
            }
        }
        else {
            location.setGround(new Dirt());
        }
    }
}
