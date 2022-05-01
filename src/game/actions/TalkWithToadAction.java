package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Toad;
import game.enums.Status;
import java.util.ArrayList;


public class TalkWithToadAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        // check if player has a wrench
        boolean hasWrench = actor.hasCapability(Status.HAS_WRENCH);
        // check if player has powerstar effect
        boolean hasPowerStarEffect = actor.hasCapability(Status.INVINCIBLE);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String sentence;

        // Checkings
        if (hasWrench && !hasPowerStarEffect) {
            indexes.add(1); indexes.add(2); indexes.add(3);
        }
        else if (hasWrench && hasPowerStarEffect) {
            indexes.add(2); indexes.add(3);
        }
        else if (!hasWrench && hasPowerStarEffect) {
            indexes.add(0); indexes.add(2); indexes.add(3);
        }
        else {
            indexes.add(0); indexes.add(1); indexes.add(2); indexes.add(3);
        }
        int index = Utils.getRandomFrom(indexes);
        sentence = Toad.getSentence(index);
        return "Toad: "+"\""+sentence+"\"";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with Toad";
    }

    @Override
    public String hotkey() {
        return null;
    }
}
