package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Player;
import game.actors.Toad;
import game.enums.Status;
import game.weapons.Wrench;

import java.util.ArrayList;

public class TalkWithToadAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        // check if player has a wrench
        boolean hasWrench = actor.getWeapon() instanceof Wrench;
        // check if player has powerstar effect
        boolean hasPowerStarEffect = actor.hasCapability(Status.INVINCIBLE);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String sentence;
        if (hasWrench && !hasPowerStarEffect) {
            indexes.add(1); indexes.add(2); indexes.add(3);
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }
        else if (hasWrench && hasPowerStarEffect) {
            indexes.add(2); indexes.add(3);
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }
        else if (!hasWrench && hasPowerStarEffect) {
            indexes.add(2); indexes.add(3);
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }
        else {
            indexes.add(0); indexes.add(1); indexes.add(2); indexes.add(3);
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }

        Display display = new Display();
        display.println(sentence);
        return "You have conversed with Toad!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with Toad.";
    }

    @Override
    public String hotkey() {
        return "d";
    }
}
