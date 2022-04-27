package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.interfaces.Enemy;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}


//Overridden method from Ground class to not allow Actors that are enemies into Floor grounds. As per implementation requirements
	public boolean canActorEnter( Actor actor ) {
		boolean retVal = true;

		if ( actor instanceof Enemy ) {
			retVal = false;
		}

		return retVal;
	}
}
