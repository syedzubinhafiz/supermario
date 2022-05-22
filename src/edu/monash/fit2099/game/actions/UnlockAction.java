package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.Monologue;
import edu.monash.fit2099.game.actors.PrincessPeach;


public class UnlockAction extends Action {

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


    @Override
    public String menuDescription(Actor actor) {
        return "Use the key to free Princess Peach & end the game!";
    }

}
