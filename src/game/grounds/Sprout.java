package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.enums.Status;

public class Sprout extends Tree {

    public Sprout() {
        super('+');
        this.allowableActions = new ActionList();
        this.turnCounter = 0;
        this.success_rate = 0.9;
        this.damage=10;
    }

    public void tick(Location location){
        if (this.hasCapability(Status.RESET)){
            //
        }
        turnCounter++;
        if (Utils.getRandomBias() <= 0.1 && (!location.containsAnActor())) {
            location.addActor(new Goomba());
        }
        if (turnCounter == 10) {
            location.setGround(new Mature());
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public JumpAction getJumpAction(Location location) {
        return new JumpAction(location, success_rate, damage);
    }
}
