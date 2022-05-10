package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.actions.*;
import edu.monash.fit2099.game.enums.Status;

import java.util.Random;

public class Bowser extends Enemy{

    private boolean turnToSpeak;
    /**
     * Constructor.

     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.behaviours.remove(10); //remove the wander behaviour from enemies
        this.behaviours.remove(1);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(super.playTurn(actions, lastAction, map, display) != null) {
            return super.playTurn(actions, lastAction, map, display);
        }
        else if ( !this.isConscious()) {
            //drop key
            return new GetRemovedAction();
        }
        else if(turnToSpeak()) {
            Display d = new Display();
            String s = Monologue.getSentence("Bowser");
            d.println(s);
        }

        //find for player
        Random random = new Random();
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if ((destination.getActor() != null && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY))) {
                AttackAction a = new AttackAction(destination.getActor(), exit.getName());
                a.execute(this,map);
                return (new FireAttackAction(destination.getActor(), exit.getName()));
            }
        }

        return new DoNothingAction();


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


}
