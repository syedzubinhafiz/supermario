package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.Monologue;

public class PrincessPeach extends Actor{


    private boolean turnToSpeak;
    private boolean allowedToLeave;

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 100);
    }



    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Display d = new Display();
        if(turnToSpeak()){
            String s = Monologue.getSentence("Princess Peach");
            d.println(s);
        }
        return new DoNothingAction();
    }

//    @Override
//    public TalkAction getTalkAction(Actor otherActor) {
//        return new TalkAction(monologues, this);
//    }
//
//    public TalkAction getTalkAction() {
//        return new TalkAction(monologues, this);
//    }


    public boolean turnToSpeak() {
        if(turnToSpeak) {
            turnToSpeak=false;
            return true;
        }
        turnToSpeak=true;
        return false;
    }

    public boolean isAllowedToLeave() {
        return allowedToLeave;
    }

    public void setAllowedToLeave(boolean allowedToLeave) {
        this.allowedToLeave = allowedToLeave;
    }

}
