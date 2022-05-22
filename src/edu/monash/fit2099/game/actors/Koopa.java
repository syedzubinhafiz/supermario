package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.game.actions.DestroyShellAction;
import edu.monash.fit2099.game.actions.DormantAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;


/**
 * Abstract Class housing all basic features and functions of all Koopa types; Dormancy, behaviours and damage
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public abstract class Koopa extends Enemy {


    /**
     * Attribute
     * DormantAction attribute to store an instance of dormantAction for the Koopa to use
     */
    private final DormantAction dormantState;


    /**
     * Constructor of Koopa
     * @param name String describing the type of Koopa.
     * @param displayChar Character to be displayed on the map.
     * @param hitPoints Integer used to initialise the health of the Koopa.
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        dormantState = new DormantAction( this, 'D' );
        this.addCapability(Status.HAS_DORMANCY);
        this.addCapability(Status.FOLLOW);
        this.intrinsicDamage=30;
    }


    /**
     * Method to provide access to SetDisplayChar from the Actor abstract class, since it is protected and final in Actor class and we cannot change engine code,
     * other classes cannot call on this except for subclasses.
     * @param displayChar the new displayChar to be set
     */
    public void callSetDisplayChar( char displayChar ){
        this.setDisplayChar( displayChar );
    }


    /**
     * Method to return an ActionList containing the general allowedActions that all Koopas provide to actors when near it.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList that stores the allowable actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (this.hasDormancy() && otherActor.hasCapability(Status.HAS_WRENCH)) {
            actions.add(new DestroyShellAction(this, direction));
        }

        else if( otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasDormancy() ) {
            actions.add( super.getAttackedAction( otherActor, this, direction ) );
            // get AttackAction using the parent class's method
            // Note: When actor is invincible: Remove Koopa & Koopa doesn't get to hide in shell,
            //       and it doesn't drop SuperMushroom (completely obliterated). (From ED: Teaching team did this).
        }

        return actions;
    }


    /**
     * Method to return general actions that needs to be done for the current turn of all Koopa types.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be played
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(super.playTurn(actions, lastAction, map, display) != null) {
            return super.playTurn(actions, lastAction, map, display);
        }
        else if ( this.hasCapability(Status.DORMANT) ) {
            return new DoNothingAction();
        }
        else if ( !this.isConscious() && !this.hasDormancy() ) {
            return dormantState;
        }

        if(turnToSpeak()) {
            speak(name);
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }


    /**
     * Method to see if Koopa is Dormant or not.
     * @return true if Koopa is Dormant, false otherwise
     */
    public boolean hasDormancy() {
        return this.hasCapability(Status.DORMANT);
    }


    /**
     * Overrides the getWeapon() method to return only the intrinsicWeapon of Koopa.
     * @see Actor#getWeapon()
     */
    @Override
    public Weapon getWeapon() {
        return this.getIntrinsicWeapon();
    }


    /**
     * Overrides the getIntrinsicWeapon class to return the specific intrinsic weapon Koopa has.
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

}
