package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.items.Key;


/**
 * Special InstaKilledAction that allows an actor to instantly kill its opponents.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class InstaKilledAction extends AttackAction {


    /**
     * Constructor.
     * @param target    the target actor to attack
     * @param direction the direction of attack
     */
    public InstaKilledAction(Actor target, String direction) {
        super(target, direction);
    }


    /**
     * Executes the InstaKillAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Weapon weapon = actor.getWeapon();

        int damage = weapon.damage();
        while (target.isConscious()) {
            target.hurt(damage);
        }
        result = actor + " instantly kills " + target + "!";

        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            map.removeActor(target);
        }
        return result;
    }

}

