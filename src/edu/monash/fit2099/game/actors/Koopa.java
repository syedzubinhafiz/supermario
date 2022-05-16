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
import edu.monash.fit2099.game.actions.InstaKilledAction;
import edu.monash.fit2099.game.actions.Monologue;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.Resettable;


/**
 * Class that represents a Koopa (like a turtle), who is an enemy to the player.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class Koopa extends Enemy implements Resettable {

    /**
     * DormantAction attribute to store an instance of dormantAction
     */
    private final DormantAction dormantState;
    private boolean turnToSpeak;


    /**
     * Constructor of Koopa
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        dormantState = new DormantAction( this, 'D' );
        this.addCapability(Status.HAS_DORMANCY);
        this.addCapability(Status.FOLLOW);
        this.intrinsicDamage=30;
    }

    public Koopa(String name, char f, int i) {
        super(name, f, i);
        dormantState = new DormantAction( this, 'D' );
        this.addCapability(Status.HAS_DORMANCY);
        this.addCapability(Status.FOLLOW);
        this.intrinsicDamage=30;
    }


    /**
     * Method to call SetDisplayChar from the Actor abstract class, since it is protected and final in Actor class,
     * other classes cannot call on this.
     * @param displayChar the new displayChar to be set
     */
    public void callSetDisplayChar( char displayChar ){
        this.setDisplayChar( displayChar );
    }

    /**
     * Method to return an ActionList containing the allowedActions Koopa provides to actor when actor is near it.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList that stores the allowable actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if  (otherActor.hasCapability(Status.INVINCIBLE)) {
            actions.add(new InstaKilledAction(this, direction));
        }
        else if (this.hasDormancy() && otherActor.hasCapability(Status.HAS_WRENCH)) {
            actions.add(new DestroyShellAction(this, direction));
        }
        //As per implementation requirement, "Try to attack Koopa until it is unconscious...
        //...It will hide inside its shell, so the display character should change to D.  You must NOT have an attack action to it anymore."
        else if( otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasDormancy() ) {
            actions.add( super.getAttackedAction( otherActor, this, direction ) );
            // get AttackAction using the parent class's method
        }

        return actions;
    }


    /**
     * Method to return the action that needs to be done for the current turn of the Koopa.
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

        Display d = new Display();
        if(turnToSpeak()) {
            String s = Monologue.getSentence("Koopa");
            d.println(s);
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            System.out.println("BEHAVIOUR"+behaviour);
            if (action != null) {
                System.out.println("BEHAVIOUR RETURNED"+behaviour);
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Implements the resetInstance() method in Resettable interface.
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        // be killed
        this.addCapability(Status.RESET);
    }

    /**
     * Method to see if Koopa is Dormant or not.
     * @return true if Koopa is Dormant, false otherwise
     */
    public boolean hasDormancy() {
        return this.hasCapability(Status.DORMANT);
    }

    @Override
    /**
     * Overrides the getWeapon() method to return only the intrinsicWeapon of Koopa.
     * @see Actor#getWeapon()
     */
    public Weapon getWeapon() {
        return this.getIntrinsicWeapon();
    }

    @Override
    /**
     * Overrides the getIntrinsicWeapon class to return the specific intrinsic weapon Koopa has.
     * @see Actor#getIntrinsicWeapon()
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
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
    public void setIntrinsicDamage( int intrinsicDamage ) {
        this.intrinsicDamage = intrinsicDamage;
    }

    //Added this
    public int getIntrinsicDamage(){
        return this.intrinsicDamage;
    }

}
