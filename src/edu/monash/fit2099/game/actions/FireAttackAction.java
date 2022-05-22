package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actors.Enemy;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.items.Fire;


/**
 * FireAttackAction class represents the special attack action Mario gains after consuming the FireFlower
 * @author: Syed Zubin Hafiz
 * @version: 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class FireAttackAction extends AttackAction {


    /**
     * Constructor for FireAttackAction
     * @param target
     * @param direction
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }


    /**
     * This method is responsible to execute the FireAttackAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result which is a String when enemy is getting attacked with fire attack effect
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location location = map.locationOf(target);

        if (actor.hasCapability(Status.INVINCIBLE)) {
            InstaKilledAction instaKilledAction = new InstaKilledAction(target, direction);
            result = instaKilledAction.execute(actor, map);
            result += " And ";
        }
        location.addItem(new Fire(map));

        if (target.isConscious() && target.hasCapability(Status.FOLLOW) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ((Enemy) target).addFollowBehaviour(actor);
        }
        if (actor.hasCapability(Status.FOLLOW) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ((Enemy) actor).addFollowBehaviour(target);
        }
        result += actor + " has successfully attacked " + target + " with fire!";
        if(!target.isConscious()) {
            map.removeActor(target);
        }
        return result;
    }


    /**
     * This method adds to the menu description when a successful fire attack action has been performed
     * @param actor The actor performing the certain action
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }

}

