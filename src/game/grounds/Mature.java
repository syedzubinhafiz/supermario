package game.grounds;


import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Koopa;
import game.enums.Status;


import java.util.ArrayList;
import java.util.List;

public class Mature extends Tree {

    public Mature() {
        super('T', 0.7, 30,  "Mature");
    }


    @Override
    public void tick(Location location){
        super.tick(location);
        turnCounter++;
        if (Utils.getRandomBias() <= 0.15 && !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
        else if (Utils.getRandomBias() <= 0.2) {
            location.setGround(new Dirt());
        }
        else if (turnCounter % 5 == 0) {

            // make an empty list of all surrounding exits that have fertile ground in the game map
            List<Exit> matureExits = new ArrayList<Exit>();
            for (Exit exit : location.getExits()){
                if (exit.getName() == "North" || exit.getName() == "South" || exit.getName() == "East" || exit.getName() == "West") {
                    if (exit.getDestination().getGround().getDisplayChar() == '.') {
                        matureExits.add(exit);
                    }
                }
            }

            ArrayList<Integer> indexes = new ArrayList();
            for (int i=0;i < matureExits.size();i++){
                indexes.add(i);
            }
            if ((matureExits.size() > 0 && indexes.size() > 0)) {
                matureExits.get(Utils.getRandomFrom(indexes)).getDestination().setGround(new Sprout());
            }
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public JumpAction getMovementAction(Location location, double success, int damage, String direction) {
        return new JumpAction(location, success, damage, direction, getName());
    }


}
