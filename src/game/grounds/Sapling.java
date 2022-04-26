///*
//package game.grounds;
//
//import edu.monash.fit2099.engine.actions.ActionList;
//import edu.monash.fit2099.engine.positions.Ground;
//import edu.monash.fit2099.engine.positions.Location;
//import game.actors.Goomba;
//import game.interfaces.Resettable;
//
//public class Sapling extends Tree implements Resettable {
//
//
//    public Sapling() {
//        super('+');
//        setStage(TreeCycleStage.SPROUT);
//        this.allowableActions = new ActionList();
//        Resettable.super.registerInstance();
//    }
//
//    public void tick(){
//        turnCounter++;
//        if (utils.getRandomBias() <= 0.1) {
//            Ground ground = new Ground('+') {
//
//                // Shouldn't do this because super.tick() should be called in the overriden tick() method as
//                // this one inside this method doesn't actually get called.
//                @Override
//                public void tick(Location location) {
//                    super.tick(location);
//                    location.addActor(new Goomba());
//                }
//            };
//            if (turnCounter == 10) {
//                setStage(TreeCycleStage.SAPLING);
//            }
//        }
//    }
//}
//*/
