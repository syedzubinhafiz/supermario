package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AddToWalletAction;
import game.enums.Status;
import game.interfaces.Resettable;

public class Coin extends Item implements Resettable {

    private int coinValue;

    public Coin(int coinValue) {
        super("Coin", '$', false);
        this.coinValue = coinValue;
        Resettable.super.registerInstance();
    }

    public int getCoinValue() {
        return coinValue;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        System.out.println("yesh");
        return new AddToWalletAction(this);
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    //for coins ON THE GROUND (those picked up
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
        }
        else {
            super.tick(currentLocation);
        }
    }

}
