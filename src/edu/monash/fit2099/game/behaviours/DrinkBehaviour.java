package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.actions.ConsumeAction;
import edu.monash.fit2099.game.actions.DrinkAction;
import edu.monash.fit2099.game.enums.Status;
import edu.monash.fit2099.game.grounds.MagicalFountain;
import edu.monash.fit2099.game.interfaces.Behaviour;

public class DrinkBehaviour implements Behaviour {

    public DrinkBehaviour(){
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(map.locationOf(actor).getGround().hasCapability(Status.FOUNTAIN)) {
            MagicalFountain fountain = (MagicalFountain) map.locationOf(actor).getGround();
            return new DrinkAction(fountain.getWater(), fountain);
        }
        return null;
    }
}
