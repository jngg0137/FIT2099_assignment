package game.actions;

import edu.monash.fit2099.engine.items.Item;
import game.actors.SuspiciousTrader;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import edu.monash.fit2099.engine.actions.Action;

public class TradeAction extends Action {
    private Item remembrance;
    private SuspiciousTrader trader;

    public TradeAction(Item remembrance, SuspiciousTrader trader) {
        this.remembrance = remembrance;
        this.trader = trader;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            return trader.trade(remembrance, player);
        }
        return "This action is only available to the Tarnished.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + remembrance;
    }
}
