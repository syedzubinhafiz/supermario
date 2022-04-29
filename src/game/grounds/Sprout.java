package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.enums.Status;

public class Sprout extends Tree {

    public Sprout() {
        super('+');
        this.turnCounter = 0;
        this.success_rate = 0.9;
        this.damage=10;
    }
    @Override
    public String getName() {
        return "Sprout";
    }

    public void tick(Location location){
        super.tick(location);

        turnCounter++;
        if (Utils.getRandomBias() <= 0.1 && (!location.containsAnActor())) {
            Goomba g = new Goomba();
            location.addActor(g);
        }
        if (turnCounter == 10) {
            location.setGround(new Sapling());
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public JumpAction getJumpAction(Location location, double success, int damage, String direction) {
        return new JumpAction(location, success, damage, direction, getName());
    }


}
