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
        while (turnCounter <= 3) {
            if (location.getActor() != null) {
                location.getActor().hurt(20);
            }
            location.setGround(new Dirt());
        }
    }
}
