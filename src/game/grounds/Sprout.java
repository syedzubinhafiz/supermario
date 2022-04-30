package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Goomba;

public class Sprout extends Tree {

    /**
     * Constructor for the Sprout Class which overrides the Tree's constructor using super
     * and sets the final success rate,damage and name for this particular stage
     */
    public Sprout() {
        super('+', 0.9, 10, "Sprout");
    }


    @Override
    /**
     * Checks the randomBias attained and see if it agrees with the probability while also checking if there's another actor
     * on the current ground location. If preconditions match, it spawns a Goomba.
     * After 10 turns spawns a sapling in the current location of the sprout.
     *
     * @param location Location of the ground
     */
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
