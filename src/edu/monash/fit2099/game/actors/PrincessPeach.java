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

/**
 * Singleton Class representing the Princess Peach.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class PrincessPeach extends Actor implements Resettable, Speakable {

    /**
     * Whether or not its her turn to speak
     */
    private boolean turnToSpeak;
    /**
     * Whether or not the princess is locked by bowser
     */
    private boolean locked;
    /**
     * Original location of princess peach
     */
    private static Location original;

    /**
     * Singleton princess peach instance
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

    /**
     * Gets the instance of Princess Peach
     * @return Princess Peach instance
     */
    public static PrincessPeach getInstance() {
        if (instance == null) {
            instance = new PrincessPeach();
        }
        return instance;
    }

    /**
     * Method to return the action that needs to be done for the current turn of the Princess.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be played
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
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

    /**
     * Returns whether or not it is the speaker's turn to speak (alternating).
     * @return boolean
     */
    public boolean turnToSpeak() {
        if(turnToSpeak) {
            turnToSpeak=false;
            return true;
        }
        turnToSpeak=true;
        return false;
    }

    /**
     * Whether or not princess is locked
     * @return boolean
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Set if she is locked
     * @param locked boolean for if she is locked or not
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    @Override
    /**
     * Implements the resetInstance() method in Resettable interface.
     * @see Resettable#resetInstance()
     */
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    /**
     * Sets the original location of princess
     * @param location original location
     */
    public static void setLocation(Location location) {
        original = location;
    }
}
