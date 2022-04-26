package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.interfaces.Resettable;

public class Koopa extends Actor implements Resettable {


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        Resettable.super.registerInstance();
    }

    public Koopa() {
        super("Koopa", 'K', 100);
        Resettable.super.registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)) {
            map.removeActor(this);
        }
        return null;
    }

    @Override
    public void resetInstance() {
        // be killed
        this.addCapability(Status.RESET);
    }
}
