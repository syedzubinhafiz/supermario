package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Player;
import game.actors.Toad;
import game.enums.Status;

import java.util.ArrayList;

public class TalkWithToadAction extends Action {

    private Player player;

    public TalkWithToadAction(Player player) {
        this.player=player;
    }

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
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }
        else {
            indexes.add(0); indexes.add(1); indexes.add(2); indexes.add(3);
            sentence = Toad.getSentence(Utils.getRandomFrom(indexes));
        }

        return sentence;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "You have conversed with Toad!";
    }

    @Override
    public String hotkey() {
        return "d";
    }
}
