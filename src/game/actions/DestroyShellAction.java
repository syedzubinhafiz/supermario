package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

public class DestroyShellAction extends AttackAction {

    protected Actor target;
    protected String direction;
    protected SuperMushroom mushroomDrop;


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
        map.removeActor( actor );
        map.locationOf(actor).addItem(mushroomDrop);
        return null;
    }
}
