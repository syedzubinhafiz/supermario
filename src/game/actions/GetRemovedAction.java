package game.actions;

import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class GetRemovedAction extends DoNothingAction {
    /**
     * Constructor
     */
    public GetRemovedAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is removed from map.";
    }

    @Override
    public String hotkey() {
        return null;
    }

}
