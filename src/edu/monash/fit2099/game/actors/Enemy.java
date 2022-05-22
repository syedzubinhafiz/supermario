package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.actions.FireAttackAction;
import edu.monash.fit2099.game.actions.GetRemovedAction;
import edu.monash.fit2099.game.actions.InstaKilledAction;
import edu.monash.fit2099.game.behaviours.AttackBehaviour;
import edu.monash.fit2099.game.behaviours.DrinkBehaviour;
import edu.monash.fit2099.game.behaviours.FollowBehaviour;
import edu.monash.fit2099.game.behaviours.WanderBehaviour;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.Drinker;
import edu.monash.fit2099.game.interfaces.Resettable;
import edu.monash.fit2099.game.interfaces.Speakable;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class that represents an Enemy to the Actor
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public abstract class Enemy extends Actor implements Resettable, Drinker, Speakable {

    /**
     * Attribute
     * Integer representing the intrinsic/base damage of the Enemy
     */
    protected int intrinsicDamage;


    /**
     * Attribute
     * Boolean attribute to flag when the Enemy is supposed to speak
     */
    private boolean turnToSpeak;


    /**
     * Map of behaviours to store the behaviour of the enemy along with its priority.
     * It is protected visibility to allow child-classes to have access.
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    /**
     * Constructor.
     * @param name name of the Enemy
     * @param displayChar the display char of the Enemy
     * @param hitPoints the amount of hitPoints it has
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(5, new DrinkBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        Resettable.super.registerInstance();
        this.addCapability(Status.MUST_JUMP);
    }


    /**
     * Method to return an Action that would be executed for the actor in the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action    the Action to be executed
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)) {
            return new GetRemovedAction();
        }

        return null;
    }


    /**
     * Adds a follow behaviour to the Enemy to follow the actor
     * @param actor the actor to be followed
     */
    public void addFollowBehaviour(Actor actor) {
        this.behaviours.put(2, new FollowBehaviour(actor));
    }


    /**
     * Gets an attack action
     * @param targetActor the targetAction to be attacked
     * @param direction   the direction of attack
     * @return AttackAction an AttackAction
     */
    public AttackAction getAttackedAction(Actor otherActor, Actor targetActor, String direction) {
        if(otherActor.hasCapability(Status.FIRE_ATTACK_EFFECT)){
            return new FireAttackAction(targetActor, direction);
        } else if (otherActor.hasCapability(Status.INVINCIBLE) ){
            return new InstaKilledAction(targetActor, direction);
        }
        return new AttackAction( targetActor, direction );
    }


    /**
     * Implements the resetInstance() method in Resettable interface.
     * Sets a RESET capability in the enum Status class to invoke the Reset functionality
     * on the Tree class.
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }


    /**
     * Setter for the intrinsic damage attribute of the Enemy
     * @param intrinsicDamage value for damage to be set to
     */
    public void setIntrinsicDamage( int intrinsicDamage ){
        this.intrinsicDamage = intrinsicDamage;
    }


    /**
     * Gets the attribute related to intrinsic damage of the Enemy
     * @return Integer representing the current damage value of the enemy
     */
    public int getIntrinsicDamage(){
        return this.intrinsicDamage;
    }


    /**
     * Method to check if it is the Enemy entities turn to speak.
     * @return True/False representing whether the Enemy should speak.
     */
    public boolean turnToSpeak() {
        if(turnToSpeak) {
            turnToSpeak=false;
            return true;
        }
        turnToSpeak=true;
        return false;
    }

}

