package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

/**
 * Special DestroyShellAction that allows an actor to destroy the shell of a Koopa.
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game.actions
 */
public class DestroyShellAction extends AttackAction {


    /**
     * The SuperMushroom that will be dropped.
     */
    SuperMushroom mushroomDrop;

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the direction of attack
     */
    public DestroyShellAction(Actor target, String direction) {
        super(target, direction);
        mushroomDrop = new SuperMushroom();
    }


    /**
     * Executes the DestroyShellAction by dropping a SuperMushroom on the location & removing the actor (Koopa) from the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //think about implementing mushroom to drop from koopa inventory, mention how you could implement future game mechanics about stunning koopas or certain weapons have
        //chances to allow players to make enemies drop their items
        map.locationOf(target).addItem(mushroomDrop);
        map.removeActor(target);
        return "You have destoyed "+ target+"(dormant)";
    }

    /**
     * Returns a descriptive statement for the DestroyShellAction
     * @param actor The actor performing the certain action
     * @return a String describing actor destroying the shell
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys the "+ target + "(dormant) at "+direction;
    }
}
