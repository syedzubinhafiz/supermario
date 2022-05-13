package edu.monash.fit2099.game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.HigherGround;

/**
 * A class that figures out an Action that will allow the actor to wander around the map.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.behaviours
 */
public class WanderBehaviour extends Action implements Behaviour {

	/**
	 * Final Random object attribute
	 */
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();

		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
			else if ( !map.isAnActorAt(destination) && actor.hasCapability(Status.MUST_JUMP)
					&& destination.getGround().hasCapability(Status.HIGHER_GROUND)) {
				HigherGround g = (HigherGround) destination.getGround();
				return g.getFinalMovementAction(actor, destination,exit.getName());
			}
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	@Override
	/**
	 * Executes the action of wandering.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a String to show the message output after execution.
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive statement for the wandering behaviour
	 * @param actor The actor peforming the action
	 * @return a String describing the actor wandering
	 */
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
