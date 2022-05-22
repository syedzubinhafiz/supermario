package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Enemy;

public class AttackAndFollowAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the direction of attack
     */
    public AttackAndFollowAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Display d = new Display();
        AttackAction a = new AttackAction(target, direction);
        String result = a.execute(actor, map);

        // attack & follow the actor
        ((Enemy) actor).addFollowBehaviour(target);
        if(!result.toUpperCase().contains("MISSES")) {
            if(target.isConscious()) {
                FireAttackAction f = new FireAttackAction(target, direction);
                if (result.toUpperCase().contains("IMMUNE")) {
                    result += " But since Mario is not immune to fire, ";
                } else {
                    result += " And ";
                }
                result += f.execute(actor, map);
            }
        }
        if(!target.isConscious()) {
            map.removeActor(target);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
