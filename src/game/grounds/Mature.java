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

    /**
     * Constructor for the Mature stage
     * Overrides the constructor for tree to change the display char for Mature and sets the appropriate success rate,
     *damage and name for this particular Tree stage
     */
    public Mature() {
        super('T', 0.7, 30,  "Mature");
    }


    @Override
    /**
     * Overrides Ground's tick() method
     * Spawns a Koopa if the randomBias attained agrees with the probability and if there are no other actors in the current
     * location.
     * After every 5 turns played, a sprout is spawned in either of the four directions given it has 'dirt'
     *
     *
     * @param location Location of the ground
     */
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
    /**Sets a RESET capability in the enum Status class to invoke the Reset functionality on the Tree class*/
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
