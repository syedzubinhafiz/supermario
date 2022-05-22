package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Enemy;
import edu.monash.fit2099.game.enums.Status;

public class AttackAndFireAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the direction of attack
     */
    public AttackAndFireAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        AttackAction a = new AttackAction(target, direction);
        String result = a.execute(actor, map);

        // attack & follow the actor
        if(actor.hasCapability(Status.FOLLOW)) {
            ((Enemy) actor).addFollowBehaviour(target);
        }
        if(!result.toUpperCase().contains("MISSES") && target.isConscious()) {
            if (result.toUpperCase().contains("IMMUNE")) {
                result += " But since Mario is not immune to fire, ";
            } else {
                result += " And ";
            }
            FireAttackAction f = new FireAttackAction(target, direction);
            result += f.execute(actor, map);
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
