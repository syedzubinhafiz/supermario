package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.Utils;
import game.interfaces.HigherGround;

import java.util.HashMap;

public class JumpAction extends Action {
    private final Location location;
    private final double SUCCESS_RATE;
    private final int DAMAGE;
    private final String DIRECTION;
    private final String NAME;


    // create hash map - not needed (remove)
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

    public JumpAction(Location location, double SUCCESS_RATE, int DAMAGE, String direction, String name) {
        this.location = location;
        this.SUCCESS_RATE = SUCCESS_RATE;
        this.DAMAGE = DAMAGE;
        this.DIRECTION = direction;
        this.NAME = name;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if(Utils.getRandomBias()<=SUCCESS_RATE){
                map.moveActor(actor,this.location); //jump onto the surface
                result = "You have successfully jumped onto the " + this.NAME+"!";
                return result;
        }


        else {
            actor.hurt(DAMAGE);
            result = "Oh no! You couldn't make the jump and fell down :(. \n" +
                    "Damage Received: " + this.DAMAGE;

            return result;
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + this.DIRECTION + " " + this.NAME + " ("+ this.location.x() + ", " + this.location.y()+")";
    }

    @Override
    public String hotkey() {
        return null;
    }
}
