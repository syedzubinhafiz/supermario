
package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;

public class Sapling extends Tree{


    public Sapling() {
        super('t');
        this.allowableActions = new ActionList();
        this.turnCounter = 0;
        this.success_rate = 0.8;
        this.damage=20;
        this.jumpActionProvided=false;
    }

    public void tick(Location location){
        turnCounter++;
        if (Utils.getRandomBias() <= 0.1) {
            location.addItem(new Coin(20));
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
    public JumpAction getJumpAction(Location location, double success, int damage, String direction) {
        return new JumpAction(location, success, damage, direction);
    }


}

