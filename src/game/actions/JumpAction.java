package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.Utils;

import java.util.HashMap;

public class JumpAction extends Action {
    private Location location;
    private final double SUCCESS_RATE;
    private final int DAMAGE;
    private final String DIRECTION;


    // create hash map
    public static HashMap<String, String> newmap = new HashMap<String, String>() {{
        put("South-West", "1");
        put("South", "2");
        put("South-East", "3");
        put("West", "4");
        put("East", "6");
        put("North-West", "7");
        put("North", "8");
        put("North-East", "9");
    }};

    public JumpAction(Location location, double SUCCESS_RATE, int DAMAGE, String direction) {
        this.location = location;
        this.SUCCESS_RATE = SUCCESS_RATE;
        this.DAMAGE = DAMAGE;
        this.DIRECTION = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(!actor.hasCapability(Status.TALL)){
            if(Utils.getRandomBias()<=SUCCESS_RATE){
                map.moveActor(actor,this.location); //jump onto the surface
                result = "You have successfully jumped onto the higher ground!";
                return result;
            }
        else if (actor.hasCapability(Status.TALL)){
                map.moveActor(actor,this.location);
                result =  "You have successfully jumped onto the higher ground!";
                return result;
        }
        else actor.hurt(DAMAGE);
            result = "Oh no! You couldn't make the jump and fell down :( ";
            return result;
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + this.DIRECTION;
    }

    @Override
    public String hotkey() {
        return newmap.get(this.DIRECTION);
    }
}
