package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Goomba;

public class Sprout extends Tree {

    public Sprout() {
        super('+', 0.9, 10, "Sprout");
    }


    @Override
    public void tick(Location location){
        super.tick(location);

        turnCounter++;
        if (Utils.getRandomBias() <= 0.1 && (!location.containsAnActor())) {
            Goomba g = new Goomba();
            location.addActor(g);
        }
        if (turnCounter == 10) {
            location.setGround(new Sapling());
        }
    }





}
