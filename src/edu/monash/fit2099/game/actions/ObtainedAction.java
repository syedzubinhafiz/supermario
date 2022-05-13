package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Toad;
import edu.monash.fit2099.game.interfaces.Obtainable;

public class ObtainedAction extends Action {
    private Obtainable item;

    public ObtainedAction(Obtainable item){
        this.item = item;
    }
    @Override
    public String execute(Actor actor, GameMap map) {

        item.obtainedBy(actor);
        // remove from toad
        Toad.removeObtainableItem(item);
        return item + " is obtained by " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains " + item;
    }
}
