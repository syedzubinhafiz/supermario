package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.actions.AttackAndFireAction;
import edu.monash.fit2099.game.actions.GetRemovedAction;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.items.Key;

/**
 * The main enemy of Mario who keeps Princess Peach.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class Bowser extends Enemy {

    /**
     * Its original location on the map
     */
    Location original;
    /**
     * Singleton bowser instance
     */
    private static Bowser instance;

    /**
     * Constructor
     * @param location original location
     */
    private Bowser(Location location) {
        super("Bowser", 'B', 500);
        this.behaviours.remove(10); // remove the wander behaviour
        this.behaviours.remove(1); // remove the attack action
        this.addItemToInventory(new Key());
        original = location;
        this.intrinsicDamage=80;
        this.addCapability(Status.FOLLOW);
    }

    /**
     * Returns the singleton instance
     * @param location original location
     * @return an instance of bowser
     */
    public static Bowser getInstance(Location location) {
        if (instance == null) {
            instance = new Bowser(location);
        }
        return instance;
    }

    /**
     * Setter for intrinsicDamage
     * @param intrinsicDamage value for damage to be set to
     */
    @Override
    public void setIntrinsicDamage( int intrinsicDamage ) {
        this.intrinsicDamage = intrinsicDamage;
    }

    /**
     * Getter for intrinsic dmaage
     * @return the damage
     */
    @Override
    public int getIntrinsicDamage() {
        return this.intrinsicDamage;
    }

    /**
     * Method to return general actions that needs to be done for the current turn of Bowser
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be played
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(super.playTurn(actions, lastAction, map, display) != null && isConscious()) {
            // move bowser back to original position if its not already at original position
            if(map.locationOf(this)!=original) {
                if (!map.isAnActorAt(original)) {
                    map.moveActor(this, original);
                } else {
                    // find empty exits
                    for (Exit exit : original.getExits()) {
                        Location destination = exit.getDestination();
                        if (!map.isAnActorAt(destination)) {
                            map.moveActor(this, destination);
                        }
                    }
                }
            }

            // heal to maximum
            heal(500);

            // stand there until mario is within attack range (remove follow behaviour if there is)
            if(behaviours.containsKey(2)) {
                behaviours.remove(2);
            }

            return new DoNothingAction();
        }
        else if ( !this.isConscious()) {
            return new GetRemovedAction();
        }
        else if(turnToSpeak()) {
            speak(name);
        }

        // attack & follow player
        AttackAction attackToActor = getAttackAction(map);
        if (attackToActor!=null) {
            return attackToActor;
        }

        // if attack was unsuccessful or there was no attack, get one of its behaviours (could be a follow behaviour)
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Gets the AttackAction it should perform.
     * @param map Gamemap
     * @return the AttackAction
     */
    public AttackAction getAttackAction(GameMap map) {
        //find for player
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                return new AttackAndFireAction(destination.getActor(), exit.getName());
            }
        }
        return null;
    }

    /**
     * Method to return an ActionList containing the general allowedActions that Bowser provide to actors when near it.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList that stores the allowable actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add( super.getAttackedAction( otherActor, this, direction ) );
            //New way to get AttackAction using the interface's method
        }
        return actions;
    }

    /**
     * Overrides the getIntrinsicWeapon class to return the specific intrinsic weapon Bowser has.
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "punch");
    }




}
