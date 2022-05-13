package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.actions.InstaKilledAction;
import edu.monash.fit2099.game.actions.Monologue;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.Resettable;

public class PiranhaPlant extends Enemy implements Resettable  {

    private boolean turnToSpeak;
    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.remove(10); // remove the wander behaviour from enemies
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Display d = new Display();

        if(super.playTurn(actions, lastAction, map, display) != null) {
            //increase hp by 50 & heal to max
            this.increaseMaxHp(50);
        }
        if(turnToSpeak()) {
            String s = Monologue.getSentence("Piranha");
            d.println(s);
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
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if  (otherActor.hasCapability(Status.INVINCIBLE) &&otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
            actions.add(new InstaKilledAction(this, direction));
        }
        else if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add( super.getAttackedAction(otherActor, this, direction ) );
            //New way to get AttackAction using the interface's method
        }
        return actions;
    }

    public boolean turnToSpeak() {
        if(turnToSpeak) {
            turnToSpeak=false;
            return true;
        }
        turnToSpeak=true;
        return false;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     * Implements the resetInstance() method in Resettable interface.
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }


}
