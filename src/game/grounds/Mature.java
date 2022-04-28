package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Koopa;
import game.enums.Status;
import game.interfaces.Resettable;
import game.items.Coin;

import java.util.ArrayList;
import java.util.List;

public class Mature extends Tree {

    public Mature() {
        super('T');
        this.allowableActions = new ActionList();
        this.turnCounter = 0;
        this.success_rate = 0.7;
        this.damage=30;
        this.jumpActionProvided=false;
    }

    public void tick(Location location){
        turnCounter++;
        if (Utils.getRandomBias() <= 0.15 && !location.containsAnActor()) {
                location.addActor(new Koopa());
        }
        else if (Utils.getRandomBias() <= 0.2) {
            location.setGround(new Dirt());
        }
        else if (turnCounter % 5 == 0) {
            // find list of dirt destinations
            List<Exit> dirtDestinations = new ArrayList<Exit>();
            // make an empty list of all exits in the game map
            List<Exit> matureExits = new ArrayList<Exit>();
            for (Exit exit : location.getExits()){
                if (exit.getName() == "North" || exit.getName() == "South" || exit.getName() == "East" || exit.getName() == "West") {
                    matureExits.add(exit);
                }
            }
            //make an list to store all dirt destinations
            for (int i = 0; i < matureExits.size(); i++) {
                if (matureExits.get(i).getDestination().getGround().getDisplayChar() == '.') {
                    dirtDestinations.add(matureExits.get(i));
                }
            }
            ArrayList<Integer> indexes = new ArrayList();
            for (int i=0;i < dirtDestinations.size();i++){
                indexes.add(i);
            }
            if ((dirtDestinations.size() > 0)) {
                dirtDestinations.get(Utils.getRandomFrom(indexes)).getDestination().setGround(new Sprout());
            }
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
