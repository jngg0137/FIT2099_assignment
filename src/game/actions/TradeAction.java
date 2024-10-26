package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.SuspiciousTrader;
import game.items.RemembranceOfDancingLion;
import game.items.RemembranceOfFurnaceGolem;

import java.util.List;

public class TradeAction extends Action {
    private final Player player;
    private final SuspiciousTrader trader;

    public TradeAction(Player player, SuspiciousTrader trader) {
        this.player = player;
        this.trader = trader;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> inventory = player.getInventory();

        for (Item item : inventory) {
            // Check for specific remembrance items in player's inventory and trade
            if (item instanceof RemembranceOfDancingLion || item instanceof RemembranceOfFurnaceGolem) {
                return trader.trade(item, player);
            }
        }
        return "You don't have any valid items to trade.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can trade items with " + trader;
    }
}
