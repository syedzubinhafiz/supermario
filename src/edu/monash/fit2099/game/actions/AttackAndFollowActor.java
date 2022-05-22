package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class AttackAndFollowActor extends AttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the direction of attack
     */
    public AttackAndFollowActor(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Display d = new Display();
        AttackAction a = new AttackAction(actor, direction);
        String result = a.execute(actor, map);

        d.println(result); // attack & follow the actor
        if(result.toUpperCase().contains("MISSES")) {
            return null;
        }
        else {
            FireAttackAction f = new FireAttackAction(actor, direction);
            f.execute(actor, map);
        }

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
