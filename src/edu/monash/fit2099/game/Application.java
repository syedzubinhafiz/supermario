package edu.monash.fit2099.game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import edu.monash.fit2099.game.actors.*;
import edu.monash.fit2099.game.grounds.*;
import edu.monash.fit2099.game.items.Bottle;
import edu.monash.fit2099.game.items.PowerStar;
import edu.monash.fit2099.game.items.SuperMushroom;
import edu.monash.fit2099.game.weapons.Wrench;

/**
 * The main class for the Mario World edu.monash.fit2099.game.
 *
 */
public class Application {

	/**
	 * Main method in driver class
	 * @param args arguments from command line
	 */
	public static void main(String[] args) {

			World world = new World(new Display());



			// FIRST MAP
			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());
			List<String> map1 = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				".................++.........................#...................................",
				".............................................##.................................",
				"..........+....................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				"............................................+....##.............................",
				"................................................##..............................",
				"........................................+#____####..............................",
				".......................................+#_____###...............................",
				".......................................+#______###....+.........................",
				"........................................+#_____###..............................",
				".................................................##.............................",
				"............................+......................#............................",
				"....................................................#...........................",
				"............................................+........#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap1 = new GameMap(groundFactory, map1);
			world.addGameMap(gameMap1);
			// add toad here
			Actor toad = Toad.getInstance();

			// add singleton bottle
			Bottle bottle = Bottle.getInstance();

			Toad.getTradeableInventory().add(new PowerStar());
			Toad.getTradeableInventory().add(new SuperMushroom());
			Toad.getTradeableInventory().add(new Wrench());

			//Changed to account for singleton bottle class, ED mentioned only one bottle at a time
			Toad.getObtainables().add( bottle );
			gameMap1.at(43, 11).addActor(toad);
			gameMap1.at(43, 14).setGround(new PowerFountain());
			gameMap1.at(42, 14).setGround(new HealthFountain());


			// SECOND MAP
			FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava());
			List<String> map2 = Arrays.asList(
					"......................................................",
					"......................................................",
					"......................................................",
					"......................................................",
					"......................................................",
					"......................................................",
					"......................................................",
					"......................................................");

			GameMap gameMap2 = new GameMap(groundFactory2, map2);
			world.addGameMap(gameMap2);
			// Add some lava to second map
			gameMap2.at(2, 1).setGround(new Lava());
			gameMap2.at(0, 1).setGround(new Lava());
			gameMap2.at(9, 0).setGround(new Lava());
			gameMap2.at(11, 1).setGround(new Lava());
			// Add princess peach & bowser in gameMap2
			gameMap2.at(3, 1).addActor(PrincessPeach.getInstance(gameMap2.at(3, 1)));
			gameMap2.at(4, 1).addActor(Bowser.getInstance(gameMap2.at(4, 1)));


			// ADD WARP PIPES
			// IN GAMEMAP1
			gameMap1.at(40,8).setGround(new WarpPipe(gameMap2));
			gameMap1.at(38,8).setGround(new WarpPipe(gameMap2));
			gameMap1.at(10,18).setGround(new WarpPipe(gameMap2));
			gameMap1.at(53,13).setGround(new WarpPipe(gameMap2));

			// IN GAMEMAP2
			// set reference from map2's wrap pipe to map1 after map1 has been created
			WarpPipe warpPipe = new WarpPipe(gameMap1);
			warpPipe.setSecondMap(true);
			gameMap2.at(0,0).setGround(warpPipe);


			// Add player
			Actor mario = new Player("Mario", 'm', 100);
			world.addPlayer(mario, gameMap1.at(42, 10));

			// (for debugging) add koopa
//			Actor koopa = new Koopa();
//			gameMap1.at(46, 10).addActor(koopa);

			world.run();

	}
}
