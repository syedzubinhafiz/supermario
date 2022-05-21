package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.game.interfaces.Resettable;


/**
 * Class that represents a Koopa (like a turtle), who is an enemy to the player.
 *
 * @author Vanessa Khoo Ming Yi
 * @version 1.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class WalkingKoopa extends Koopa implements Resettable {


    /**
     * Constructor of Koopa
     */
    public WalkingKoopa() {
        super("Koopa", 'K', 100);
    }


}
