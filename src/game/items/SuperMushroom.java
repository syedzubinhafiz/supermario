package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.TradeAction;
import game.actors.Player;
import game.enums.Status;
import game.interfaces.ConsumableItem;
import game.interfaces.Tradeable;


import static java.lang.Character.toUpperCase;

public class SuperMushroom extends Item implements Tradeable, ConsumableItem {

    private final int VALUE = 400;
    private final int healthIncrease = 50;
    private final Status buffStatus = Status.TALL;
    private boolean isConsumed;
    private ConsumeAction consumeAction;

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        isConsumed=false;
        consumeAction=null;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //add a consumeAction after picking up
        if (!isConsumed) {
            if(this.consumeAction !=null){
                removeAction(this.consumeAction);
            }
            this.consumeAction = getConsumeAction(actor);
            addAction(this.consumeAction);
        }
        else {
            if(this.consumeAction != null) {
                removeAction(this.consumeAction);
                this.consumeAction=null;
            }
        }
        super.tick(currentLocation, actor);
    }


    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }

    @Override
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE);
    }

    @Override
    public Tradeable newInstance() {
        return new PowerStar();
    }
    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }

    @Override
    public void consumedBy(Actor actor) {
        actor.increaseMaxHp( healthIncrease );
        ((Player)actor).callSetDisplayChar( toUpperCase(actor.getDisplayChar()) );
        actor.addCapability( buffStatus );
        setIsConsumed(true);
    }

    @Override
    public boolean canFade() {
        return false;
    }

    private void setIsConsumed(boolean b) {
        isConsumed=b;
    }

}
