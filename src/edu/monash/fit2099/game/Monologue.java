package edu.monash.fit2099.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Monologue {

    // responsible for handling all the sentences
    static ArrayList<String> allStrings = new ArrayList<String>(Arrays.asList(
            "Dear Mario, I'll be waiting for you...",
            "Never gonna give you up!",
            "Release me, or I will kick you!",
            "You might need a wrench to smash Koopa's hard shells.",
            "You better get back to finding the Power Stars.",
            "The Princess is depending on you! You are our only hope.",
            "Being imprisoned in these walls can drive a fungus crazy :(",
            "What was that sound? Oh, just a fire.",
            "Princess Peach! You are formally invited... to the creation of my new kingdom!",
            "Never gonna let you down!",
            "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!",
            "Mugga mugga!",
            "Ugha ugha... (Never gonna run around and desert you...)",
            "Ooga-Chaka Ooga-Ooga!",
            "Never gonna make you cry!",
            "Koopi koopi koopii~!",
            "Pam pam pam!",
            "Slsstssthshs~! (Never gonna say goodbye~)",
            "Ohmnom nom nom nom.",
            "I am free! Thank you Mario, how will I ever repay you..."));

    public static boolean hasWrench=false;
    public static boolean hasInvibility=false;
    public static boolean isLocked=true;


    public static String getSentence(String actor) {
        String result="";
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        if (actor=="Princess Peach") {
            if(isLocked) {
                indexes.add(0);
                indexes.add(1);
                indexes.add(2);
            } else {
                indexes.add(19);
            }
        } else if (actor=="Toad") {
            // Different sentences based on the scenario
            indexes.add(3); indexes.add(4); indexes.add(5); indexes.add(6);
            if(hasWrench) {
                indexes.remove(0);
            }
            if (hasInvibility) {
                indexes.remove(1);
            }
        } else if (actor=="Bowser") {
            indexes.add(7); indexes.add(8); indexes.add(9); indexes.add(10);
        } else if (actor=="Goomba") {
            indexes.add(11); indexes.add(12); indexes.add(13);
        } else if (actor=="Koopa") {
            indexes.add(14); indexes.add(15);
        } else if (actor=="Flying Koopa") {
            indexes.add(14); indexes.add(15); indexes.add(16);
        } else if (actor=="Piranha") {
            indexes.add(17); indexes.add(18);
        }
        int index = Utils.getRandomFrom(indexes);
        result= allStrings.get(index);
        return  actor + ": \"" + result+"\"";
    }

    public static void setHasWrench(boolean hasWrench) {
        Monologue.hasWrench = hasWrench;
    }

    public static void setHasInvibility(boolean hasInvibility) {
        Monologue.hasInvibility = hasInvibility;
    }

    public static void setIsLocked(boolean isLocked) {
        Monologue.isLocked = isLocked;
    }

}
