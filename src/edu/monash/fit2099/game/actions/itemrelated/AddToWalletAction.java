package edu.monash.fit2099.game.actions.itemrelated;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actors.Player;
import edu.monash.fit2099.game.items.Coin;
import edu.monash.fit2099.game.items.Wallet;

/**
 * Special PickUpItemAction that allows an actor to add coin items to wallet.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actions
 */
public class AddToWalletAction extends PickUpItemAction {

    /**
     * Coin attribute that was picked up
     */
    Coin coin;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public AddToWalletAction(Item item) {
        super(item);
        this.coin = (Coin) item;
        // since item is a private attribute in PickUpItemAction, it is NOT inherited, so we need to introduce another attribute for Coin
    }

    @Override
    /**
     * Executes the add to wallet action by adding the value to actor's wallet balance and removing the item from the location.
     * from the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to show the message output after execution.
     * @see PickUpItemAction#execute(Actor actor, GameMap map)
     */
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        Wallet w = ((Player) actor).getWallet();
        w.addBalance(coin.getCoinValue());
        return actor + " adds the " + coin + " to its Wallet.";
    }

    @Override
    /**
     * Returns a descriptive statement for the AddToWalletAction
     * @param actor The actor performing the certain action
     * @return a String describing actor adding coin to wallet
     */
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor) + " ("+ coin.getCoinValue()+")";
    }
}
