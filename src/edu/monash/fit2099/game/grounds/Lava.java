package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;

public class Lava extends Ground {
    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    public void tick(Location location) {
        // inflict damage on actor at location if actor is there
        if(location.getActor() != null) {
            location.getActor().hurt(15);
            System.out.println("Lava has hurt "+ location.getActor()+" by 15 damage!");
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_STAND_LAVA);
    }
}
