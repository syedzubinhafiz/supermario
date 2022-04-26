package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.actors.Koopa;
import game.enums.Status;
import game.grounds.Dirt;
import game.interfaces.HigherGround;
import game.interfaces.Resettable;
import game.items.Coin;


import java.util.*;

public class Tree extends Ground implements HigherGround, Resettable {
    private final double SUCCESS_RATE_SPROUT = 0.9;
    private final double SUCCESS_RATE_SAPLING = 0.8;
    private final double SUCCESS_RATE_MATURE = 0.7;
    private final int DAMAGE_SPROUT = 10;
    private final int DAMAGE_SAPLING = 20;
    private final int DAMAGE_MATURE = 30;



    public enum TreeCycleStage {
        SPROUT, SAPLING, MATURE;
    }

    private int turnCounter;
    private TreeCycleStage stage;
    Utils utils = new Utils();
    protected ActionList allowableActions;

    //setter for treecyclestage
    public void setStage(TreeCycleStage stage) {
        this.stage = stage;
    }



    /**
     * Constructor.
     */
    public Tree() {
        super('+');
        setStage(TreeCycleStage.SPROUT);
        this.allowableActions = new ActionList();
        Resettable.super.registerInstance();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET) && (Utils.getRandomBias() <= 0.5)) {
            Dirt d = new Dirt();
            location.setGround(d);
        }
        else if (stage == TreeCycleStage.SPROUT) {
            sproutTick();
        } else if (stage == TreeCycleStage.SAPLING) {
            saplingTick();
        } else
            matureTick();
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (actor != location.getActor() && actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            this.allowableActions.add(getJumpAction(location));
        }
        return allowableActions;
    }

    @Override
    public JumpAction getJumpAction(Location location) {
        if (stage == TreeCycleStage.SPROUT) {
            return new JumpAction(location, SUCCESS_RATE_SPROUT, DAMAGE_SPROUT);
        } else if (stage == TreeCycleStage.SAPLING) {
            return new JumpAction(location, SUCCESS_RATE_SAPLING, DAMAGE_SAPLING);
        } else return new JumpAction(location, SUCCESS_RATE_MATURE, DAMAGE_MATURE);
    }

    @Override
    public JumpAction getJumpAction() {
        return null;
    }

    public void sproutTick() {
        turnCounter++;
        if (utils.getRandomBias() <= 0.1) {
            Ground ground = new Ground('+') {

                // Shouldn't do this because super.tick() should be called in the overriden tick() method as
                // this one inside this method doesn't actually get called.
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
        if (utils.getRandomBias() <= 0.2) {
            Ground ground = new Ground('t') {

                // Shouldn't do this because super.tick() should be called in the overriden tick() method as
                // this one inside this method doesn't actually get called.
                @Override
                public void tick(Location location) {
                    super.tick(location);
                    location.addItem(new Coin(20));
                }
            };
        }
        if (turnCounter == 10) {
            setStage(TreeCycleStage.MATURE);
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.MUST_JUMP) && actor.hasCapability(Status.INVINCIBLE)) {
            return true;
        }
        else if (actor.hasCapability(Status.MUST_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
            return false;
        }
        return false;
    }

    public void matureTick() {
        turnCounter = 0;
        turnCounter++;

        List<Exit> dirtDestination = new ArrayList<Exit>();
        if (utils.getRandomBias() <= 0.15) {
            Ground ground = new Ground('T') {

                // Shouldn't do this because super.tick() should be called in the overriden tick() method as
                // this one inside this method doesn't actually get called.
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
            if (utils.getRandomBias() <= 0.20) {
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
