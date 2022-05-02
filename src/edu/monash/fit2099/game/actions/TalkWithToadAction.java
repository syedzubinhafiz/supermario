package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.Utils;
import edu.monash.fit2099.game.enums.Status;
import java.util.ArrayList;

/**
 * Special TalkWithToadAction that allows an actor to talk with Toad.
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.actions
 */
public class TalkWithToadAction extends Action {

    /**
     * An array that stores the sentences that Toad can speak out.
     */
    private static final String[] sentences = new String[] {"You might need a wrench to smash Koopa's hard shells.",
            "You better get back to finding the Power Stars.", "The Princess is depending on you! You are our only hope.",
            "Being imprisoned in these walls can drive a fungus crazy :("};


    /**
     * Executes the TalkWithToadAction to allow a message by Toad to be printed in the console.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see Action#execute(Actor actor, GameMap map)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // check if player has a wrench
        boolean hasWrench = actor.hasCapability(Status.HAS_WRENCH);
        // check if player has powerstar effect
        boolean hasPowerStarEffect = actor.hasCapability(Status.INVINCIBLE);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String sentence;

        // Different sentences based on the scenario
        if (hasWrench && !hasPowerStarEffect) {
            indexes.add(1); indexes.add(2); indexes.add(3);
        }
        else if (!hasWrench && hasPowerStarEffect) {
            indexes.add(0); indexes.add(2); indexes.add(3);
        }
        else if (hasWrench && hasPowerStarEffect) {
            indexes.add(2); indexes.add(3);
        }
        else {
            indexes.add(0); indexes.add(1); indexes.add(2); indexes.add(3);
        }
        int index = Utils.getRandomFrom(indexes);
        sentence = getSentence(index);
        return "Toad: "+"\""+sentence+"\"";
    }

    /**
     * Method to get the sentences that is at a specific index.
     * @param index index of the array to be used
     * @return String sentence that was chosen
     */
    public String getSentence(int index) {
        return sentences[index];
    }

    /**
     * Returns a descriptive statement for the TalkWithToadAction
     * @param actor The actor performing the certain action
     * @return a String describing actor talking with Toad
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with Toad";
    }

}
