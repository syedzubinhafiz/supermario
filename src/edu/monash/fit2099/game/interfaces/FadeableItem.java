package edu.monash.fit2099.game.interfaces;

/**
 * Interface for Fadeable items
 *
 * @author: Vanessa Khoo Ming Yi
 * @version: 1.0.0
 * @see: edu.monash.fit2099.edu.monash.fit2099.game.interfaces
 */
public interface FadeableItem {

    /**
     * Method to be implemented that returns an integer that represents the current turns left
     * for the fadeable item.
     * @return int num of turns the fadeable item has left before it fades
     */
    int getFadingTimeOnFloorInventory();
}
