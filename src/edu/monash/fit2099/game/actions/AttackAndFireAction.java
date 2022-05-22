package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Enemy;
import edu.monash.fit2099.game.enums.Status;

/**
 * Class representing the attack of normal Attack combined with Fire Action
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actions
 */
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
    /**
     * This method is responsible to execute the AttackAndFireAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result which is a String when enemy is attacked
     */
    public String execute(Actor actor, GameMap map) {
        String result = super.execute(actor, map);

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
