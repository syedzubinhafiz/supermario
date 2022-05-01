package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.actions.GetRemovedAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Resettable {

	private static int goombaCount = 0;

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		//Keep track of number of Goombas
		goombaCount += 1;
		//WHAT WE CAN DO IS IN PLAYTURN OF EACH GOOMBA, CHECK THE GOOMBACOUNT AND KILL THAT GOOMBA IF THE LIMIT IS EXCEEDED.
	}


	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add( super.getAttackedAction( this, direction ) );
			//New way to get AttackAction using the interface's method
		}


		return actions;
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		if ( Utils.getRandomBias() <= 0.10 || this.hasCapability(Status.RESET)) {
			return new GetRemovedAction();
		}

		for(Behaviour Behaviour : super.behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			// for attackBehaviour the finding of the player is done in getAction method
			// if player is not found the action would be null


			if (action != null) {
				return action;
			}

		}

		return new DoNothingAction();
	}

	@Override
	public void resetInstance() {
		// be killed (removed from map)
		this.addCapability(Status.RESET);
	}


	//getter for number of Goombas
	public int getGoombaCount() {
		return goombaCount;
	}

	@Override
	public Weapon getWeapon() {
		return this.getIntrinsicWeapon();
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}
}
