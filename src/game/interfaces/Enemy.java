package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public interface Enemy {

    AttackAction getAttackAction(Actor targetActor, String direction);

}
