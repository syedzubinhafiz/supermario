package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Player;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Random;

public class AttackBehaviour implements Behaviour {

    private final Random random = new Random();

    public AttackBehaviour() {

    }

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // find surrounding actor
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Exit exit : map.locationOf(actor).getExits()) {

            Location destination = exit.getDestination();
//            System.out.println("exit"+exit.getName()+destination.getActor());
            if ((destination.getActor() instanceof Player)) {
//                System.out.println("exit has player");
                actions.add(new AttackAction(destination.getActor(), exit.getName()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }


    }
}
