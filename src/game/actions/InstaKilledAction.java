package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

public class InstaKilledAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public InstaKilledAction(Actor target, String direction) {
        super(target, direction);
    }

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
            // remove actor if it can't be dormant
            map.removeActor(target);
        }
        return result;
    }


}
