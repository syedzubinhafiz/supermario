package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;
import game.items.Wallet;

public class AddToWalletAction extends PickUpItemAction {

    //attribute
    Coin coin;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public AddToWalletAction(Item item) {
        super(item);
        // since item is a private attribute in PickUpItemAction, it is NOT inherited, so we need to introduce another attribute for Coin
        this.coin = (Coin) item;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Wallet w = ((Player) actor).getWallet();
        w.addBalance(coin.getCoinValue());
        return actor + " adds the " + coin + " to its Wallet.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor) + " ("+ coin.getCoinValue()+")";
    }
}
