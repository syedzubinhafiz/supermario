package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

public interface HigherGround {

    JumpAction getJumpAction(Location location);

    JumpAction getJumpAction();
}
