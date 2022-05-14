package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.game.enums.Status;

public class FlyingKoopa extends Koopa {

    //Note that extension of Koopa likely does not violate LSP principle as all methods in Koopa are able to be used in FlyingKoopa

    //Might also not because of MUST_JUMP i.e if theres a method that requires koopa to return must_jump

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.removeCapability(Status.MUST_JUMP);

        this.addCapability(Status.FLYING);

    }



}
