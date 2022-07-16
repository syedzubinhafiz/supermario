package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;

/**
 * This class is used to represent a lava on the map.
 * This lava is represented as a Ground object and the player that stands on it will be hurt by 15 damage.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 3.0.0
 * @see edu.monash.fit2099.game.grounds
 */
public class Lava extends Ground {

    /**
     * Constant for the Lava damage
     */
    private static final int DAMAGE = 15;
    /**
     * Map lave belongs to
     */
    private GameMap map;

    /**
     * Constructor
     * @param map gamemap
     */
    public Lava(GameMap map) {
        super('L');
        this.map=map;
    }

    /**
     * Overrides the Ground's tick() method to incflict damage on players on it
     * @param location Location of the ground
     *
     */
    @Override
    public void tick(Location location) {
        // inflict damage on actor at location if actor is there
        if(location.getActor() != null) {
            Actor actor = location.getActor();
            actor.hurt(DAMAGE);
            Display d = new Display();
            d.println("Lava has hurt "+ actor +" by 15 damage!");
            if(!actor.isConscious() && !actor.hasCapability(Status.DORMANT)){
                map.removeActor(actor);
            }
        }
    }

    @Override
    /**
     * Returns true if an Actor can enter this location.
     *
     * If only character possesses a power star actor can enter the location,i.e walk over higher ground
     * Returns false if Actor does not have a special ability and must perform jump action to enter
     * @param actor the Actor who might be moving
     * @return true if an Actor can enter this location.
     *
     */
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_STAND_LAVA);
    }
}
