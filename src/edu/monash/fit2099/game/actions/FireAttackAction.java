package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.grounds.Fire;

public class FireAttackAction extends AttackAction {


    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(target).setGround(new Fire());
        return actor + " has attacked " + target + " with fire!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Attack "+ target + " with fire";
    }
}
