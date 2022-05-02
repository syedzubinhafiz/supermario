package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

public class DestroyShellAction extends AttackAction {


    SuperMushroom mushroomDrop;


    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public DestroyShellAction(Actor target, String direction) {
        super(target, direction);
        mushroomDrop = new SuperMushroom();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //think about implementing mushroom to drop from koopa inventory, mention how you could implement future game mechanics about stunning koopas or certain weapons have
        //chances to allow players to make enemies drop their items
        map.locationOf(target).addItem(mushroomDrop);
        map.removeActor(target);
        return "You have destoyed"+ target+"(dormant)";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys the "+ target + "(dormant) at "+direction;
    }
}
