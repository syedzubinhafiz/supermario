package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

public class ResetGameAction extends Action {

    private static ResetManager manager = ResetManager.getInstance();

    @Override
    public String execute(Actor actor, GameMap map) {
        manager.run(map);
        return "You have reset the game!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game.";
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
