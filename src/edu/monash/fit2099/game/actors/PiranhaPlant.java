package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;

public class PiranhaPlant extends Enemy {

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.remove(10); // remove the wander behaviour from enemies
        this.intrinsicDamage=90;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(super.playTurn(actions, lastAction, map, display) != null) {
            //increase hp by 50 & heal to max
            this.increaseMaxHp(50);
        }
        if(turnToSpeak()) {
            speak(name);
        }

        for(Behaviour behaviour : super.behaviours.values()) {
            Action action = behaviour.getAction(this, map);

            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add( super.getAttackedAction(otherActor, this, direction ) );
            //New way to get AttackAction using the interface's method
        }
        return actions;
    }


    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }



}
