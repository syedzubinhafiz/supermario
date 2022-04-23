package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

public interface HigherGround {
    public JumpAction getJumpAction(Location location);

}
