package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.DrinkFromFountainAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.Behaviour;


/**
 * Behaviour class that represents the drinking behaviour of the actor
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.behaviours
 */
public class DrinkBehaviour implements Behaviour {

    /**
     * Constructor
     */
    public DrinkBehaviour(){
    }


    /** Method to return the action that the actor should perform based on the drinking behaviour
     * @param actor actor with behaviour
     * @param map GameMap of the current game
     * @see Behaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(map.locationOf(actor).getGround().hasCapability(Status.FOUNTAIN)) {
            MagicalFountain fountain = (MagicalFountain) map.locationOf(actor).getGround();
            if ( fountain.getCapacity() > 0 ) {
                return new DrinkFromFountainAction(fountain.getWater(), fountain);
            }

        }
        return null;
    }

}
