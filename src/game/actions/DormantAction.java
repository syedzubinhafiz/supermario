package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Koopa;
import game.enums.Status;

public class DormantAction extends Action {

    protected Koopa target;
    protected char newDisplayChar;


    public DormantAction( Koopa toBeDormantKoopa ){
        target = toBeDormantKoopa;
        newDisplayChar = 'D';


    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.addCapability( Status.DORMANT );
        target.callSetDisplayChar( newDisplayChar );


        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

}
