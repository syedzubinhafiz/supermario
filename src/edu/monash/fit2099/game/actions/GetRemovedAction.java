package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action for removing/killing actors instantly
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.actions
 */
public class GetRemovedAction extends Action {


    @Override
    /**
     * Executes the get removed action
     * @param actor The actor to be removed
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    /**
     * Returns a descriptive statement for the GetRemovedAction
     * @param actor The actor to be killed/performing the certain action
     * @return a String describing the actor being killed/removed
     */
    public String menuDescription(Actor actor) {
        return actor + " is killed";
    }

}
