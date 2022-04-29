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
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.interfaces.Behaviour;
import game.interfaces.Enemy;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;
/**
 * A little fungus guy.
 */
public class Goomba extends Actor implements Resettable, Enemy {
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
	private static int goombaCount = 0;

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(1, new AttackBehaviour());
		Resettable.super.registerInstance();


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
			actions.add( this.getAttackedAction( this, direction ) );
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

		for(Behaviour Behaviour : behaviours.values()) {
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

	@Override
	public void addFollowBehaviour(Actor player) {
		this.behaviours.put(2, new FollowBehaviour(player));
	}

	//Implementation of enemy interface method
	@Override
	public AttackAction getAttackedAction(Actor targetActor, String direction) {
		return new AttackAction( targetActor, direction );
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
