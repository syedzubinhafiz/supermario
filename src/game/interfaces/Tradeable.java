package game.interfaces;

import game.actions.TradeAction;

public interface Tradeable {

    TradeAction getTradeAction(); // to implemented in powerstar, supermushroom & wrench

    int getValue();

    Tradeable newInstance();
}
