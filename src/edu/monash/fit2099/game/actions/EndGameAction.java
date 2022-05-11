package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actors.PrincessPeach;
import edu.monash.fit2099.game.enums.Status;

public class EndGameAction extends Action {


    public static void setPp(PrincessPeach pp1) {
        pp = pp1;
    }
    public static PrincessPeach pp;


    @Override
    public String execute(Actor actor, GameMap map) {
        String res="";
        Display d = new Display();

        Monologue.setIsLocked(false);
        String s = Monologue.getSentence("Princess Peach"); // free the princess
        d.println(s);

        // remove player from map
        map.removeActor(actor);
        res ="You have won the game!";

        return res;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Free Princess Peach & end the game!";

    }

}
