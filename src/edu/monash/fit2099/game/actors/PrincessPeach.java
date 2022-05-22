package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Resettable;
import edu.monash.fit2099.game.interfaces.Speakable;

public class PrincessPeach extends Actor implements Resettable, Speakable {

    private boolean turnToSpeak;
    private boolean locked;
    private static Location original;

    /**
     * Singleton pp instance
     */
    private static PrincessPeach instance;

    /**
     * Constructor.
     */
    private PrincessPeach() {
        super("Princess Peach", 'P', 100);
        this.addCapability(Status.INVINCIBLE);
        Resettable.super.registerInstance();
        locked = true;
    }

    public static PrincessPeach getInstance() {
        if (instance == null) {
            instance = new PrincessPeach();
        }
        return instance;
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(this.hasCapability(Status.RESET)){
            // move pp back to original position
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
        }
        if(turnToSpeak()){
            speak(name);
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    public static void setLocation(Location location) {
        original = location;
    }
}
