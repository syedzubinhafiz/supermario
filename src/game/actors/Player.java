package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ConsumeAction;
import game.actions.ResetGameAction;
import game.actions.TalkWithToadAction;
import game.enums.Status;
import game.interfaces.ConsumableItem;
import game.interfaces.Resettable;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wallet;
import game.weapons.Wrench;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private Wallet wallet;
	private int invincibilityEffectTick;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.MUST_JUMP);
		this.wallet = new Wallet();
		this.addItemToInventory(this.wallet);
		this.resetMaxHp(500);
		Resettable.super.registerInstance();
	}

	//New method to call SetDisplayChar from the Actor abstract class, instead of changing it from final to something else
	public void callSetDisplayChar( char displayChar ){
		this.setDisplayChar( displayChar );
	}


	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//reduce invincibilityEffect if there is
		// if effect <=0, this means it has worn off after the previous turn, so it is added here
		decrementInvincibility();

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// If player has NOT YET BEEN reset, add the resetgameAction
		if (!this.hasCapability(Status.RESET)) {
			actions.add(new ResetGameAction());
		}

		// VANESSA: I changed to add consumeAction to PowerStar's actions once its Picked up, same as supermushroom
		//         Thus, the below should not be needed.
		//Need to update how to implement consuming items -
		//Probably want this at the bottom to be the bottom two or top two options in console menu (aesthetic purpose)
		//item flags are present currently so actions wont be repeatedly added
		boolean itemFlagPS = false;
		boolean itemFlagSM = false;
		for ( Item item : this.getInventory() ) {

			if ( item instanceof PowerStar && !itemFlagPS ) {
				actions.add(new ConsumeAction((PowerStar) item, this));
				itemFlagPS = true;
			}
			if ( item instanceof SuperMushroom ) {
				actions.add(new ConsumeAction((SuperMushroom) item, this));
				itemFlagSM = true;
			}
			if ( itemFlagPS && itemFlagSM ) {
				break;
			}
		}

		//print player position
		Display display1 = new Display();
		display1.println(this.name+printHp()+" at ("+map.locationOf(this).x()+", "+map.locationOf(this).y()+")");

		//print wallet balance
		display1.println("wallet: $"+this.getWallet().getTotalBalance());

		// Add action to be able to talk with Toad.
		actions.add(new TalkWithToadAction());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void decrementInvincibility() {
		if(this.invincibilityEffectTick > 0) {
			invincibilityEffectTick -=1;
		}
		if (this.invincibilityEffectTick <= 0) {
			this.removeCapability(Status.INVINCIBLE);
		}
	}

	public void addInvincibility() {
		this.invincibilityEffectTick+=10;
	}

	@Override
	public void addItemToInventory(Item item) {
		super.addItemToInventory(item);

	}


	@Override
	public void resetInstance() {
		//resets player straight away here instead
		//reset player status
		if (this.hasCapability(Status.INVINCIBLE)) {
			this.removeCapability(Status.INVINCIBLE);
		} else if(this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		// heal player to maximum
		this.resetMaxHp(100);

		// Make a note that player has been reset once
		this.addCapability(Status.RESET);
		// for player ONLY, Status.RESET means that it has been reset before. For others, it means that they must be RESET in the turn.

	}

	//Method to check if player has wrench
	public boolean hasWrench() {
		boolean retVal = false;
		for (Item item : this.getInventory()) {
			if (item instanceof Wrench) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}


}
