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

    private final int healthHealAmt = 50;
    private final Status buffStatus = Status.INVINCIBLE;

    private int fadingTimeOnFloor;
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
        fadingTimeOnFloor = 0;
        fadingTimeOnPlayer = 0;
    }

    public PowerStar() {
        super("Power Star", '*', true);
        fadingTimeOnFloor = 0;
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
    public ConsumeAction getConsumeAction(PowerStar this, Player player) {
        return new ConsumeAction( this, player );
    }

    public int getHealthHealAmt(){
        return healthHealAmt;
    }

    public Status getBuffStatus(){
        return buffStatus;
    }

    public int getFadingTimeOnFloor(){
        return fadingTimeOnFloor;
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
        fadingTimeOnPlayer += 1;
        if ( fadingTimeOnPlayer >= 10 && isConsumed ) {
            ((Player)actor).removeItemFromInventory( this );
            ((Player)actor).removeCapability( buffStatus );
        }

        if ( fadingTimeOnPlayer >= 10 && !isConsumed ) {
            ((Player)actor).removeItemFromInventory( this );
        }

    }

    public void tick(Location currentLocation) {
        fadingTimeOnFloor += 1;
        if ( fadingTimeOnFloor >= 10 ) {
            currentLocation.removeItem( this );
        }
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new ConsumeAction(this,actor);
    }

    @Override
    public void consumedBy(Actor actor) {

        actor.heal( healthHealAmt );
        actor.addCapability( buffStatus );

        //This line resets the fading time of the powerStar after consumption. isConsumed is still needed to allow logic in PowerStar to remove the
        //Status away from the player once the fading duration has been reached.
        this.setFadingTimeOnPlayer( 0 );
        this.setIsConsumed( true );

    }

}
