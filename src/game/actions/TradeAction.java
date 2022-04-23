package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

public class TradeAction extends Action {

    private Player player;
    private Tradeable itemToTrade;
    private int tradeValue;
    private String hotkey;


    public TradeAction(Player player, Tradeable itemToTrade, int tradeValue, String hotkey) {
        this.player = player;
        this.itemToTrade = itemToTrade;
        this.tradeValue = tradeValue;
        this.hotkey = hotkey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Wallet wallet = this.player.getWallet
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    public String hotkey() {
        return this.hotkey;
    }
}
