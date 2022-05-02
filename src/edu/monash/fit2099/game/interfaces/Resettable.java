package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.game.managers.ResetManager;

/**
 * Interface for Resettable items
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.interfaces
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * The actual implementation of a reset happens in the tick or playTurn method of resettable instances.
     * This method is executed in a reset manager later.
     */
    void resetInstance();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     * This method is used at the constructor of `this` instance.
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }

}
