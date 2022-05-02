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
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.actors
 */
public class Goomba extends Enemy implements Resettable {


	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
	}


	/**
	 * At the moment, we only make it can be attacked by Player. This will be modified likely for the next part of our assignment (Assignment 3).
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
	 * Method to return the action that needs to be done for the current turn of the Goomba.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn.
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be played
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		if(super.playTurn(actions, lastAction, map, display) != null) {
			return super.playTurn(actions, lastAction, map, display);
		}
		else if ( Utils.getRandomBias() <= 0.10) {
			return new GetRemovedAction();
		}

		for(Behaviour Behaviour : super.behaviours.values()) {
			Action action = Behaviour.getAction(this, map);

			if (action != null) {
				return action;
			}
		}

		return new DoNothingAction();
	}

	/**
	 * Implements the resetInstance() method in Resettable interface.
	 * @see Resettable#resetInstance()
	 */
	@Override
	public void resetInstance() {
		this.addCapability(Status.RESET);
	}

	/**
	 * Overrides the getWeapon() method to return only the intrinsicWeapon of Goomba.
	 * @see Actor#getWeapon()
	 */
	@Override
	public Weapon getWeapon() {
		return this.getIntrinsicWeapon();
	}

	/**
	 * Overrides the getIntrinsicWeapon class to return the specific intrinsic weapon Goomba has.
	 * @see Actor#getIntrinsicWeapon()
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}
}
