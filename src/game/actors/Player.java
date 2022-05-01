package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetGameAction;
import game.actions.TalkWithToadAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.items.Wallet;
import game.weapons.Wrench;

import java.util.HashMap;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private Wallet wallet;



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
		this.addCapability(Status.HAS_WALLET);
		this.wallet = new Wallet();
		this.addItemToInventory(this.wallet);
		this.resetMaxHp(500);
		Resettable.super.registerInstance();
	}




	//New method to call SetDisplayChar from the Actor abstract class, since setDisplayChar is protected attribute and so cannot
	// be called in other classes
	// this is because to be extended for all other
	public void callSetDisplayChar( char displayChar ){
		this.setDisplayChar( displayChar );
	}


	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//Display object to display messages in console
		Display display1 = new Display();


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// If player has NOT YET BEEN reset, add the resetGameAction
		if (!this.hasCapability(Status.RESET)) {
			actions.add(new ResetGameAction());
		}

		//print player position
		display1.println(this.name+printHp()+" at ("+map.locationOf(this).x()+", "+map.locationOf(this).y()+")");

		//print wallet balance
		display1.println(this.getWallet() +": $"+this.getWallet().getTotalBalance());

		//print if powerstar effect is still there
		if (hasCapability(Status.INVINCIBLE)) {
			display1.println(this + " is INVINCIBLE!");
		}

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
