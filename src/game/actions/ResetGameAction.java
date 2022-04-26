package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

public class ResetGameAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager manager = ResetManager.getInstance();
        manager.run(map);

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
