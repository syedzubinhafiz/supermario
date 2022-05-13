package edu.monash.fit2099.game.actors;

import edu.monash.fit2099.game.enums.Status;

public class FlyingKoopa extends Koopa {

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.removeCapability(Status.MUST_JUMP);
    }



}
