package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;
import game.actors.Player;
import game.enums.Status;

public interface ConsumableItem {

    ConsumeAction getConsumeAction(ConsumableItem this, Actor actor);

    void consumedBy(Actor actor);
}
