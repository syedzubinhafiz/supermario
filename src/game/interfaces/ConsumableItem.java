package game.interfaces;

import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actors.Player;

public interface ConsumableItem {

    ConsumeAction getConsumeAction(ConsumableItem this, Player player);
}
