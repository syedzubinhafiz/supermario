package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.game.actions.ResetGameAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Resettable;
import edu.monash.fit2099.game.items.Wallet;


/**
 * Class representing the Player.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class Player extends Actor implements Resettable {

	/**
	 * menu represents the console menu
	 */
	private final Menu menu = new Menu();

	/**
	 * wallet item of the Player
	 */
	private final Wallet wallet;

	/**
	 * The default starting HP of the player (100 is not mentioned in requirement, but we put it as 100 first).
	 */
	private static final int DEFAULT_HP = 100;


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
		this.wallet = new Wallet(0);
		this.resetMaxHp(DEFAULT_HP);
		Resettable.super.registerInstance();
	}


	/**
	 * Method to call SetDisplayChar from the Actor abstract class, since it is protected and final in Actor class,
	 * other classes cannot call on this.
	 * @param displayChar the new displayChar to be set
	 */
	public void callSetDisplayChar( char displayChar ){
		this.setDisplayChar( displayChar );
	}


	@Override
	/**
	 * Method to return the action that needs to be done for the current turn of the Player.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn.
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be played
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
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
		display1.println("Wallet : $"+this.getWallet().getTotalBalance());

		//print if powerstar effect is still there
		if (hasCapability(Status.INVINCIBLE)) {
			display1.println(this + " is INVINCIBLE!");
		}

		// Add action to be able to talk with Toad.
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	/**
	 * Getter for the wallet attribute
	 * @return Wallet wallet item
	 */
	public Wallet getWallet() {
		return wallet;
	}

	@Override
	/**
	 * Implements the resetInstance() method in Resettable interface.
	 * @see Resettable#resetInstance()
	 */
	public void resetInstance() {
		//resets player straight away here instead of in playTurn
		if (this.hasCapability(Status.INVINCIBLE)) {
			this.removeCapability(Status.INVINCIBLE);
		} else if(this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
			this.setDisplayChar(Character.toLowerCase(getDisplayChar()));
		}
		// heal player to maximum
		this.heal(getMaxHp());

		// Make a note that player has been reset once
		this.addCapability(Status.RESET);
		// for player ONLY, Status.RESET means that it has been reset before. For others, it means that they must be RESET in the turn.

	}




}
