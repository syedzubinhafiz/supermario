package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;

import java.util.HashMap;

import static javax.swing.UIManager.put;

public class DestroyGroundAction extends MoveActorAction {

    public DestroyGroundAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    public HashMap<String, String> hotKeyMap = new HashMap<String, String> (){{
        put("North", "8");
        put("South", "2");
        put("East", "6");
        put("West", "4");
        put("North-West", "7");
        put("North-East", "9");
        put("South-West", "1");
        put("South-East", "3");
    }};

    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, this.moveToLocation); //go to surface
        moveToLocation.setGround(new Dirt());
        return super.execute(actor, map);
    }

    @Override
    public String hotkey() {
        return hotKeyMap.get(direction);
    }
}
