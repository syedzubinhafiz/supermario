package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Ground {

    int turnCounter;

    public Fire() {
        super('v');
    }

    public void tick(Location location) {
        turnCounter++;
        if (turnCounter <= 3) {
            if (location.getActor() != null) {
                location.getActor().hurt(20);
            }
        }
        else {
            location.setGround(new Dirt());
        }
    }
}
