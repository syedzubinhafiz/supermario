package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Enemy;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.Dormant;

import static java.lang.Character.toLowerCase;

/**
 * Special Action for attacking other Actors.
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
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

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
			result = target + " is immune to " + actor+"\'s attack.";
		}
        else {
        	if(actor.hasCapability(Status.INVINCIBLE)) {
        		// attack until it becomes unconscous, insta kill action not needed
				while (target.isConscious()) {
					target.hurt(damage);
				}
				result = actor + " instantly kills " + target+".";
			}
        	else {
        		target.hurt(damage);
        	}
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor if it can't be dormant
				if (!(target instanceof Dormant)) {
					map.removeActor(target);
				}
				result += System.lineSeparator() + target + " is knocked out.";
			}
		}
		if (target instanceof Enemy) {
			((Enemy) target).addFollowBehaviour(actor);
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
