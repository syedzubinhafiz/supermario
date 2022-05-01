package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class InstaKilledAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public InstaKilledAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor);
    }
}
