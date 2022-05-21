package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actors.Enemy;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.items.Fire;

public class FireAttackAction extends AttackAction {


    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location location = map.locationOf(target);

        if (actor.hasCapability(Status.INVINCIBLE)) {
            InstaKilledAction instaKilledAction = new InstaKilledAction(target, direction);
            result = instaKilledAction.execute(actor, map);
            result += " And ";
        }
        location.addItem(new Fire());

        if (target.isConscious() && target.hasCapability(Status.FOLLOW) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ((Enemy) target).addFollowBehaviour(actor);
        }
        if (actor.hasCapability(Status.FOLLOW) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ((Enemy) actor).addFollowBehaviour(target);
        }
        result += actor + " has attacked " + target + " with fire!";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}
