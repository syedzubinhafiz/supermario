package edu.monash.fit2099.game.interfaces;

/**
 * Interface for Actors that have the ability to drink Water objects.
 *
 * @author Chong Jin Yao & Vanessa Khoo
 * @version 3.0.0
 * @see edu.monash.fit2099.game.interfaces
 */
public interface Drinker {


    /**
     * Method to be implemented, responsible for setting intrinsicDamage of the Actor
     * @param intrinsicDamage Integer representing damage value
     */
    void setIntrinsicDamage(int intrinsicDamage);


    /**
     * Method to be implemented, responsible for retrieving intrinsicDamage value of the Actor
     * @return Integer representing damage value
     */
    int getIntrinsicDamage();

}
