package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.grounds.Fire;

public class FireAttackAction extends Action {
    protected Actor target;
    protected int turnCounter = 0;


    public FireAttackAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        turnCounter++;
        map.locationOf(target).setGround(new Fire());
        return "Mario has attacked " + target + " with fire!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Attack with fire";
    }
}
