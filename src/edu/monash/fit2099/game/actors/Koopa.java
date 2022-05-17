package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.game.actions.DestroyShellAction;
import edu.monash.fit2099.game.actions.DormantAction;
import edu.monash.fit2099.game.actions.InstaKilledAction;
import edu.monash.fit2099.game.actions.Monologue;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Behaviour;
import edu.monash.fit2099.game.interfaces.Resettable;


/**
 * Class that represents a Koopa (like a turtle), who is an enemy to the player.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class Koopa extends KoopaBase implements Resettable {


    /**
     * Constructor of Koopa
     */
    public Koopa() {
        super("Koopa", 'K', 100);
    }


}
