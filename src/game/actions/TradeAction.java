package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Ability;
import game.actors.Player;
import game.actors.SuspiciousTrader;

import java.util.List;

/**
 * Represents a trading action that allows a player to exchange remembrances with the SuspiciousTrader.
 * The trade grants the player new items with a set of abilities in exchange for the remembrance they trade.
 * @author Liew Zhe Xing 32619472
 */
public class TradeAction extends Action {
    private final Player player;
    private final SuspiciousTrader trader;

    /**
     * Constructor for TradeAction.
     *
     * @param player the Player initiating the trade
     * @param trader the Suspicious Trader with whom the player is trading
     */
    public TradeAction(Player player, SuspiciousTrader trader) {
        this.player = player;
        this.trader = trader;
    }

    /**
     * Executes the trade action between the player and the Suspicious Trader.
     * The trade will occur if the player's inventory contains a valid remembrance item,
     * such as Remembrance of the Dancing Lion or Remembrance of the Furnace Golem.
     *
     * @param actor the Actor performing the action (typically the player)
     * @param map   the GameMap containing the player and trader
     * @return a description of the result of the trade, or a message indicating no valid items to trade
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> inventory = player.getInventory();

        for (Item item : inventory) {
            // Check for specific remembrance items in player's inventory and trade
            if (item.hasCapability(Ability.TRADE_ITEM)) {
                return trader.trade(item, player);
            }
        }
        return "You don't have any valid items to trade.";
    }

    /**
     * Provides a description of the trade action for display in menus.
     *
     * @param actor the Actor performing the action
     * @return a string description of the trade option in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can trade items with " + trader;
    }
}
