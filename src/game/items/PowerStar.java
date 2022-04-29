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

public class PowerStar extends Item implements Tradeable, ConsumableItem {


    private final int VALUE = 600;

    private final int healthHealAmt = 200;
    private final Status buffStatus = Status.INVINCIBLE;

    private int fadingTimeOnFloorInventory;

    // These not needed anymore
    private int fadingTimeOnPlayer;
    private boolean isConsumed = false;


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        fadingTimeOnFloorInventory = 0;
        fadingTimeOnPlayer = 0;
    }

    public PowerStar() {
        super("Power Star", '*', true);
        fadingTimeOnFloorInventory = 0;
        fadingTimeOnPlayer = 0;
    }

    @Override
    public TradeAction getTradeAction() {
        return new TradeAction(this, VALUE, "a");
    }

    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public ConsumeAction getConsumeAction(PowerStar this, Actor actor) {
        return new ConsumeAction( this, actor );
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
        // These not needed anymore
        if (!isConsumed) {
            fadingTimeOnFloorInventory +=1;
        }
        else {
            fadingTimeOnPlayer += 1;
        }
        // These not needed anymore
        //        if ( fadingTimeOnPlayer >= 10 && isConsumed ) {
        //            actor.removeItemFromInventory( this );
        //            actor.removeCapability( buffStatus );
        //        }

        if ( fadingTimeOnFloorInventory >= 10 ) {
            actor.removeItemFromInventory( this );
        }

    }

    public void tick(Location currentLocation) {
        fadingTimeOnFloorInventory += 1;
        if ( fadingTimeOnFloorInventory >= 10 ) {
            currentLocation.removeItem( this );
        }
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        //add a consumeAction after picking up
        addAction(getConsumeAction(actor));
        return super.getPickUpAction(actor);
    }

    @Override
    public void consumedBy(Actor actor) {

        actor.heal( healthHealAmt );
        actor.addCapability( buffStatus );
        // remove from inventory
        actor.removeItemFromInventory(this);
        //add invincibilityeffectcounter to player
        addInvincibility((Player)actor);


        // These not needed anymore
        //This line resets the fading time of the powerStar after consumption.
        // isConsumed is still needed to allow logic in PowerStar to remove the
        // Status away from the player once the fading duration has been reached.
        this.setFadingTimeOnPlayer( 0 );
        this.setIsConsumed( true );

    }

    public void addInvincibility(Player player) {
        player.addCapability( buffStatus );
        player.addInvincibility();
    }

}
