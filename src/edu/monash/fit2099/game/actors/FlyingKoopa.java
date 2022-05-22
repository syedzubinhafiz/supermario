package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.game.enums.Status;


/**
 * Extension of Basic Koopa class for specific functionality.
 * Special Class to represent stronger Koopas( HP-wise ) that can fly freely over obstacles.
 * Still goes dormant when knocked unconscious.
 *
 * @author Chong Jin Yao
 * @version 3.0.0
 * @see edu.monash.fit2099.game.actors
 */
public class FlyingKoopa extends Koopa {

    //Note that extension of KoopaBase likely does not violate LSP principle as all methods in
    // the KoopaBase abstract class are able to be used in FlyingKoopa

    /**
     * Constructor with no params.
     * Hardcoded to provide name, display character and health specific to Flying Koopas.
     * Adds Flying Koopa-specific capabilities to the Actor.
     */
    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.removeCapability(Status.MUST_JUMP);
        this.addCapability(Status.FLYING);
    }

}
