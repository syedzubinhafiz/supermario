package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.Toad;
import game.interfaces.Tradeable;

public class TradeAction extends Action {


    private Tradeable itemToTrade;
    private int tradeValue;


    public TradeAction(Tradeable itemToTrade, int tradeValue) {
        this.itemToTrade = itemToTrade;
        this.tradeValue = tradeValue;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        boolean reduced = player.getWallet().reduceBalance(tradeValue);
        if (reduced) {
            // remove tradeableItem from Toad's inventory
            Toad.removeTradeableItem(itemToTrade);
            // Add tradeableItem to Player's inventory
            player.addItemToInventory(((Item) itemToTrade));
            return actor + " obtained " + itemToTrade;
        }
        return "You don't have enough coins!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + itemToTrade + " ($" + itemToTrade.getValue()+")";
    }


}
