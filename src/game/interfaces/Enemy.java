package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AttackAction;

public interface Enemy {


    void addFollowBehaviour(Actor actor);
    AttackAction getAttackedAction(Actor targetActor, String direction);

}
