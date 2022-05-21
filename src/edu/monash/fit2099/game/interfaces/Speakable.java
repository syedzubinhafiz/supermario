package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.game.Monologue;

public interface Speakable {

    default void speak(String key){
        Display d = new Display();
        String s = Monologue.getSentence(key);
        d.println(s);
    }


    boolean turnToSpeak();
}
