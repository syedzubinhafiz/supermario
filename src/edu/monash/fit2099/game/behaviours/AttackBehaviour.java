package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Random;

/**
 * Behaviour class that represents an attack behaviour of the actor
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.behaviours
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Random object attribute
     */
    private final Random random = new Random();


    @Override
    /** Method to return the action that the actor should perform based on the attack behaviour
     * @param actor actor with behaviour
     * @param map GameMap of the current edu.monash.fit2099.game
     * @see Behaviour#getAction(Actor, GameMap)
     */
    public Action getAction(Actor actor, GameMap map) {

        // find surrounding actor
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                actions.add(new AttackAction(destination.getActor(), exit.getName()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }


    }
}
