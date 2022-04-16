package game;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;


import java.util.*;

public class Tree extends Ground {
    public enum TreeCycleStage {
        SPROUT, SAPLING, MATURE;
    }

    private int turnCounter;
    private TreeCycleStage stage;
    Utils utils = new Utils();

    //setter for treecyclestage
    public void setStage(TreeCycleStage stage) {
        this.stage = stage;
    }

    // overriding the

    /**
     * Constructor.
     */
    public Tree() {
        super('+');
        setStage(TreeCycleStage.SPROUT);
    }

    @Override
    public void tick(Location location) {
        if (stage == TreeCycleStage.SPROUT) {
            sproutTick();
        } else if (stage == TreeCycleStage.SAPLING) {
            saplingTick();
        } else
            matureTick();
    }

    public void sproutTick() {
        turnCounter++;
        if (utils.randomBias <= 0.1) {
            Ground ground = new Ground('+') {
                @Override
                public void tick(Location location) {
                    super.tick(location);
                    location.addActor(new Goomba());
                }
            };
            if (turnCounter == 10) {
                setStage(TreeCycleStage.SAPLING);
            }
        }
    }

    public void saplingTick() {
        turnCounter = 0;
        turnCounter++;
        if (utils.randomBias <= 0.2) {
            Ground ground = new Ground('t') {
                @Override
                public void tick(Location location) {
                    super.tick(location);
                    location.addItem(new Coin());
                }
            };
        }
        if (turnCounter == 10) {
            setStage(TreeCycleStage.MATURE);
        }
    }

    public void matureTick() {
        turnCounter = 0;
        turnCounter++;
        List<Exit> dirtDestination = new ArrayList<Exit>();
        if (utils.randomBias <= 0.15) {
            Ground ground = new Ground('T') {
                @Override
                public void tick(Location location) {
                    super.tick(location);
                    location.addActor(new Koopa());
                    // make an empty list of all exits in the game map
                    List<Exit> matureExits = new ArrayList<Exit>();
                    //store all the exits in the list created
                    matureExits.add((Exit) location.getExits());
                    //make an empty list to store all dirt destinations
                    for (int i = 0; i < matureExits.size(); i++) {
                        if (matureExits.get(i).getDestination().getGround().getDisplayChar() == '.') {
                            dirtDestination.add(matureExits.get(i));
                        }
                    }
                }
            };
        }
        if (turnCounter % 5 == 0 && dirtDestination.size() != 0) {
            setStage(TreeCycleStage.SPROUT);
            if (utils.randomBias <= 0.20) {
                Ground ground = new Ground('+') {
                    @Override
                    public void tick(Location location) {
                        super.tick(location);
                        location.setGround(new Dirt());
                    }
                };
            }
        }
    }
}
