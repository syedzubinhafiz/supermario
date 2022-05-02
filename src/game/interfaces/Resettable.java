package game.interfaces;

import game.managers.ResetManager;

public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * HINT: play around with capability, the actual implementation happens in the tick or playTurn method.
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
