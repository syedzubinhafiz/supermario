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
import edu.monash.fit2099.game.actions.*;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.Resettable;
import edu.monash.fit2099.game.items.Key;

import java.util.Random;

public class Bowser extends Enemy implements Resettable {

    private boolean turnToSpeak;
    Display d = new Display();
    Location original;
    /**
     * Constructor.

     */
    public Bowser(Location location) {
        super("Bowser", 'B', 500);
        this.behaviours.remove(10); // remove the wander behaviour
        this.behaviours.remove(1); // remove the attack action
        this.addItemToInventory(new Key());
        original = location;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(super.playTurn(actions, lastAction, map, display) != null) {
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
            String s = Monologue.getSentence("Bowser");
            d.println(s);
        }

        // attack & follow player, then drop fire.
        FireAttackAction attackToActor = attackAndFollowActor(map);
        if (attackToActor!=null) {
            return attackToActor;
        }

        // else if attack was unsuccessful or there was no attack, get one of its behaviours (could be a follow behaviour)
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();


    }

    public FireAttackAction attackAndFollowActor(GameMap map) {
        //find for player
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                AttackAction a = new AttackAction(destination.getActor(), exit.getName());
                d.println(a.execute(this, map)); // attack & follow the actor
                addFollowBehaviour(destination.getActor());
                if(!destination.getActor().isConscious()) {
                    return null;
                }
                else {
                    return new FireAttackAction(destination.getActor(), exit.getName());
                }
            }
        }
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if  (otherActor.hasCapability(Status.INVINCIBLE) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
            actions.add(new InstaKilledAction(this, direction));
        }
        else if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add( super.getAttackedAction( this, direction ) );
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
        return new IntrinsicWeapon(80, "punch");
    }

    /**
     * Implements the resetInstance() method in Resettable interface.
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        // move Bowser back to the original position, heal it to maximum, and it will stand there until Mario is within Bowser's attack range
        this.addCapability(Status.RESET);
    }


}
