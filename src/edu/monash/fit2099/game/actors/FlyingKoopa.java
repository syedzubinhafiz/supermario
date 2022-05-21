package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.interfaces.Resettable;

public class FlyingKoopa extends Koopa implements Resettable {

    //Note that extension of KoopaBase likely does not violate LSP principle as all methods in
    // the KoopaBase abstract class are able to be used in FlyingKoopa

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.removeCapability(Status.MUST_JUMP);
        this.addCapability(Status.FLYING);
    }


}
