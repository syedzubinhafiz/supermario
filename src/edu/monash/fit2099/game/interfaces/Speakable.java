package edu.monash.fit2099.game.interfaces;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.game.Monologue;

public interface Speakable {

    default void speak(String name){
        Display d = new Display();
        String s = Monologue.getSentence(name);
        d.println(s);
    }


    boolean turnToSpeak();
}
