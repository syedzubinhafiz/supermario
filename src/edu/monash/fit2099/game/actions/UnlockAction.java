package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.Monologue;
import edu.monash.fit2099.game.actors.PrincessPeach;


/**
 * Special action class tasked with giving the Player the ability to free Princess Peach when in possession of a key.
 *
 * @author Chong Jin Yao & Team1
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class UnlockAction extends Action {


    /**
     * Executes the UnlockAction by getting the instance of Singleton Class PrincessPeach and setting
     * its locked attribute to false.
     * Then removes the player from the map and prints any necessary dialogue from Princess Peach.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String letting the player know they have completed the game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        Display d = new Display();

        PrincessPeach.getInstance().setLocked(false);

        String sentence = Monologue.getSentence("Princess Peach"); // free the princess
        d.println(sentence);

        // remove player from map
        map.removeActor(actor);
        message = "You have won the game!";
        return message;
    }


    /**
     * Returns a descriptive statement for UnlockAction.
     * @param actor The actor performing the action.
     * @return a String to describe the UnlockAction for menu use.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use the key to free Princess Peach & end the game!";
    }

}
