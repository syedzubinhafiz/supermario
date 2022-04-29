package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DestroyShellAction;
import game.actions.DormantAction;
import game.actions.GetRemovedAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Dormant;
import game.interfaces.Enemy;
import game.interfaces.Resettable;
import game.weapons.Wrench;

import java.util.HashMap;
import java.util.Map;

public class Koopa extends Actor implements Resettable, Enemy, Dormant {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    //CHECK FOR ERROR
    private DormantAction dormantState;

//    /**
//     * Constructor.
//     *
//     * @param name        the name of the Actor
//     * @param displayChar the character that will represent the Actor in the display
//     * @param hitPoints   the Actor's starting hit points
//     */
//Commented out, incase some error, comment back in
//    public Koopa(String name, char displayChar, int hitPoints) {
//        super(name, displayChar, hitPoints);
//        this.behaviours.put(10, new WanderBehaviour());
//        Resettable.super.registerInstance();
//    }

    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        Resettable.super.registerInstance();
        dormantState = new DormantAction( this );
    }

    //New method to call SetDisplayChar from the Actor abstract class, instead of changing it from final to something else
    public void callSetDisplayChar( char displayChar ){
        this.setDisplayChar( displayChar );
    }


    @Override
    public void addFollowBehaviour(Actor player) {
        this.behaviours.put(2, new FollowBehaviour(player));
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //Check for better way to check for wrench
        if(otherActor instanceof Player) { // we assume enemies cannot pick up items, thus this should only be for Player
            if (this.hasDormancy() && ((Player) otherActor).hasWrench()) {
                actions.add(new DestroyShellAction(this, direction));
            }
        }

        //As per implementation requirement, "Try to attack Koopa until it is unconscious...
        //...It will hide inside its shell, so the display character should change to D.  You must NOT have an attack action to it anymore."
        if( otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasDormancy() ) {

            actions.add( this.getAttackedAction( this, direction ) );
            //New way to get AttackAction using the interface's method
        }



        return actions;
    }


    //Implementation of enemy interface method
    @Override
    public AttackAction getAttackedAction(Actor targetActor, String direction) {
        return new AttackAction( targetActor, direction );
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //for checking
        if (this.hasDormancy()) {
            return new GetRemovedAction();
        }

        else if ( this.hasCapability(Status.DORMANT) ) {
            return new DoNothingAction();
        }

        else if ( !this.isConscious() && !this.hasDormancy()) {
            return dormantState;
        }


        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);

            if (action != null) {
                return action;
            }

        }

        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        // be killed
        this.addCapability(Status.RESET);
    }

    public boolean hasDormancy() {
        return this.hasCapability(Status.DORMANT);
    }

    @Override
    public Weapon getWeapon() {
        return this.getIntrinsicWeapon();
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }
}
