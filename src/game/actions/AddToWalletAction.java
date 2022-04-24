package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;
import game.items.Wallet;

public class AddToWalletAction extends PickUpItemAction {
    // might change to be removed


    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public AddToWalletAction(Item item) {
        super(item);
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }
}
