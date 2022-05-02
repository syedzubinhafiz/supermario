package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.managers.ResetManager;

/**
 * Special ResetGameAction that allows an actor to reset the edu.monash.fit2099.game.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class ResetGameAction extends Action {

    /**
     * Attribute to store the singleton ResetManager instance.
     */
    private static final ResetManager manager = ResetManager.getInstance();

    @Override
    /**
     * Executes the resetting of the edu.monash.fit2099.game.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    public String execute(Actor actor, GameMap map) {
        manager.run(map);
        return "You have reset game";
    }

    @Override
    /**
     * Returns a descriptive statement for the ResetGameAction
     * @param actor The actor performing the certain action
     * @return a String describing actor resetting the edu.monash.fit2099.game
     */
    public String menuDescription(Actor actor) {
        return "Reset the game.";
    }

    @Override
    /**
     * Returns the specific hotkey for this action.
     * @return String representing hotkey
     */
    public String hotkey() {
        return "r";
    }
}
