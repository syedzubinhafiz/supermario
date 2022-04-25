package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.AddToWalletAction;

public class Coin extends Item {

    private int coinValue;

    public Coin(int coinValue) {
        super("Coin", '$', false);
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new AddToWalletAction(this);
    }
}
