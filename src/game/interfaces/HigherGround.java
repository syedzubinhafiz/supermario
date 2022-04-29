package game.interfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;

public interface HigherGround {

    String getName();

    JumpAction getJumpAction(Location location, double success, int damage, String direction);

}
