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
import edu.monash.fit2099.game.actions.AttackAndFollowActor;
import edu.monash.fit2099.game.actions.FireAttackAction;
import edu.monash.fit2099.game.actions.GetRemovedAction;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.items.Key;


public class Bowser extends Enemy {

    Location original;
    /**
     * Singleton bow instance
     */
    private static Bowser instance;

    /**
     * Constructor.

     */
    private Bowser(Location location) {
        super("Bowser", 'B', 500);
        this.behaviours.remove(10); // remove the wander behaviour
        this.behaviours.remove(1); // remove the attack action
        this.addItemToInventory(new Key());
        original = location;
        this.intrinsicDamage=80;
    }

    public static Bowser getInstance(Location location) {
        if (instance == null) {
            instance = new Bowser(location);
        }
        return instance;
    }


    @Override
    public void setIntrinsicDamage( int intrinsicDamage ) {
        this.intrinsicDamage = intrinsicDamage;
    }

    @Override
    public int getIntrinsicDamage() {
        return this.intrinsicDamage;
    }

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

        // attack & follow player, then drop fire.

//        FireAttackAction attackToActor = attackAndFollowActor(map);
//        if (attackToActor!=null) {
//            return attackToActor;
//        }
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

    public AttackAction getAttackAction(GameMap map) {
        //find for player
        Display d = new Display();
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                return new AttackAndFollowActor(destination.getActor(), exit.getName());
            }
        }
        return null;
    }

    public FireAttackAction attackAndFollowActor(GameMap map) {
        //find for player
        Display d = new Display();
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                AttackAction a = new AttackAction(destination.getActor(), exit.getName());
                String result = a.execute(this, map);
                d.println(result); // attack & follow the actor
                addFollowBehaviour(destination.getActor());
                if(result.toUpperCase().contains("MISSES")) {
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

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add( super.getAttackedAction( otherActor, this, direction ) );
            //New way to get AttackAction using the interface's method
        }
        return actions;
    }


    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "punch");
    }




}
