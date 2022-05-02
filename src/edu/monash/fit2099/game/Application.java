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
import edu.monash.fit2099.game.items.PowerStar;
import edu.monash.fit2099.game.items.SuperMushroom;
import edu.monash.fit2099.game.weapons.Wrench;

/**
 * The main class for the Mario World edu.monash.fit2099.game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);
			// add toad here
			Actor toad = Toad.getInstance();
			Toad.getTradeableInventory().add(new PowerStar());
			Toad.getTradeableInventory().add(new SuperMushroom());
			Toad.getTradeableInventory().add(new Wrench());
			world.addPlayer(toad, gameMap.at(43, 11));


			Actor mario = new Player("Mario", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			world.run();

	}
}
