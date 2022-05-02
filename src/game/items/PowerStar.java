package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.TradeAction;
import game.enums.Status;
import game.interfaces.ConsumableItem;
import game.interfaces.FadeableItem;
import game.interfaces.Tradeable;

public class PowerStar extends Item implements Tradeable, ConsumableItem, FadeableItem {


    private final int VALUE = 600;
    private final int healthHealAmt = 200;
    private final Status buffStatus = Status.INVINCIBLE;
    private int fadingTimeOnFloorInventory;
    private int fadingTimeOnPlayer;
    private boolean isConsumed;
    private ConsumeAction consumeAction;

    /***
//     * Constructor.
//     *  @param name the name of this Item
//     * @param displayChar the character to use to represent this item if it is on the ground
//     * @param portable true if and only if the Item can be picked up
//     */
    public PowerStar() {
        super("Power Star", '*', true);
        fadingTimeOnFloorInventory = 10;
        fadingTimeOnPlayer = 10;
        isConsumed=false;
        consumeAction=null;
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
    public boolean canFade() {
        return true;
    }

    @Override
    public int getValue() {
        return VALUE;
    }

    public int getHealthHealAmt(){
        return healthHealAmt;
    }

    public Status getBuffStatus(){
        return buffStatus;
    }

    public int getFadingTimeOnFloorInventory(){
        return fadingTimeOnFloorInventory;
    }

    public int getFadingTimeOnPlayer(){
        return fadingTimeOnPlayer;
    }

    public boolean getIsConsumed(){
        return isConsumed;
    }

    public void setIsConsumed( boolean isConsumed ){
        this.isConsumed = isConsumed;
    }

    public void setFadingTimeOnPlayer( int fadingTimeOnPlayer ){
        this.fadingTimeOnPlayer = fadingTimeOnPlayer;
    }

    //Ticking/Fading when powerstar in players inventory
    //Also removes item from inventory
    public void tick(Location currentLocation, Actor actor) {
        if (!isConsumed &&  !actor.hasCapability(buffStatus)) {
            if(this.consumeAction !=null){
                removeAction(this.consumeAction);
            }
            this.consumeAction = getConsumeAction(actor);
            addAction(consumeAction);
        }

        if (!isConsumed) {
            fadingTimeOnFloorInventory -= 1;
            if (fadingTimeOnFloorInventory < 0) {
                actor.removeItemFromInventory(this);
            }
        }

        else {
            if(this.consumeAction != null) {
                removeAction(this.consumeAction);
                this.consumeAction=null;
            }
            fadingTimeOnPlayer -= 1;
        }
        // if effect has worn off, remove from inventory and remove capability
        if ( fadingTimeOnPlayer < 0 && isConsumed ) {
            actor.removeItemFromInventory(this);
        }
    }

    public void tick(Location currentLocation) {
        fadingTimeOnFloorInventory -= 1;
        if ( fadingTimeOnFloorInventory <= 0 ) {
            currentLocation.removeItem( this );
        }
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
        actor.heal( healthHealAmt );
        setIsConsumed(true);
        this.addCapability(Status.INVINCIBLE);

    }

}
