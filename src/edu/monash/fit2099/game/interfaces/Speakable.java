package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.game.Monologue;


/**
 * Interface for Actors that can speak
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.interfaces
 */
public interface Speakable {


    /**
     * Default method to be implemented that allows actors who implement it to say a sentence ( speak ).
     * @param name name of Actor that is speaking
     */
    default void speak(String name){
        Display d = new Display();
        String s = Monologue.getSentence(name);
        d.println(s);
    }


    /**
     * Method to be implemented, responsible for flagging when it is the actor's turn to speak or not by returning True or False.
     * @return True or False to signal whether actor can speak or not.
     */
    boolean turnToSpeak();
}
