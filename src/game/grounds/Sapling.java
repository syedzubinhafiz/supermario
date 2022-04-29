package game.grounds;


import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;

public class Sapling extends Tree{


    public Sapling() {
        super('t', 0.8, 20, "Sapling");
    }


    @Override
    public void tick(Location location){
        super.tick(location);
        turnCounter++;
        if (Utils.getRandomBias() <= 0.1) {
            location.addItem(new Coin(20));
        }
        if (turnCounter == 10) {
            location.setGround(new Mature());
        }
    }








}

