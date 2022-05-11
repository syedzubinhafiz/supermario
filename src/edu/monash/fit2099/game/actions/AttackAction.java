package edu.monash.fit2099.game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.game.actors.Enemy;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.enums.Status;


/**
 * Special Action for attacking other Actors.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction of attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Executes the attack action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a String to show the message output after execution.
	 * @see Action#execute(Actor actor, GameMap map)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit()) && !actor.hasCapability(Status.INVINCIBLE)) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		if (target.hasCapability(Status.TALL)) {
			char newChar = Character.toLowerCase(target.getDisplayChar());
			((Player)target).callSetDisplayChar(newChar);
			target.removeCapability(Status.TALL);
		}
		if (target.hasCapability(Status.INVINCIBLE)) {
			result = target + " is immune to " + actor+ "'s attack.";
		}
        else {
        	target.hurt(damage);
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor if it can't be dormant
				if (!(target.hasCapability(Status.HAS_DORMANCY))) {
					map.removeActor(target);
				}
				result += System.lineSeparator() + target + " is knocked out.";
				//Changed to be more suitable for Koopa
			}
		}
		if (target.hasCapability(Status.FOLLOW)) {
			((Enemy) target).addFollowBehaviour(actor);
		}
		return result;
	}

	/**
	 * Returns a descriptive statement for the AttackAction
	 * @param actor The actor performing the certain action
	 * @return a String describing actor attacking a target actor at a certain direction
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
