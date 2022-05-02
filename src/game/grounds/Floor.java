package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * 
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.grounds
 */
public class Floor extends Ground {

	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}


	/**
	 * Method to check if actor can enter the Floor or not.
	 * @param actor the Actor to check
	 * @return boolean true if actor can enter floor, false otherwise
	 * @see Ground#canActorEnter(Actor) 
	 */
	public boolean canActorEnter( Actor actor ) {
		boolean retVal = true;

		if ( actor.hasCapability(Status.ENEMY) ) {
			retVal = false;
		}

		return retVal;
	}
}
