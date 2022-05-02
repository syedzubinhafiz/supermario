package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actions.AttackAction;
import game.actions.GetRemovedAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Resettable {
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour



    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        this.addCapability(Status.ENEMY);
        Resettable.super.registerInstance();
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }


    public void addFollowBehaviour(Actor player) {
        this.behaviours.put(2, new FollowBehaviour(player));
    }


    public AttackAction getAttackedAction(Actor targetActor, String direction) {
        return new AttackAction( targetActor, direction );
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}

