package edu.monash.fit2099.game.managers;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 *
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.managers
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private final List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the edu.monash.fit2099.game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     * @param map gamemap
     */
    public void run(GameMap map){
        for (int i=0;i< resettableList.size();i++) {
            // get instance & do instance.resetInstance() method
            resettableList.get(i).resetInstance();
        }
    }

    /**
     * Add the Resettable instance to the list
     * @param reset resettable instance
     */
    public void appendResetInstance(Resettable reset){
        // Add Tree, Enemies, Player, Coin
        resettableList.add(reset);
    }


    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     *
     */
    public void cleanUp(Resettable resettable){
        resettableList.remove(resettable);
    }
}
