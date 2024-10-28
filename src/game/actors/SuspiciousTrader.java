package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TradeAction;
import game.weapons.DivineBeastHead;
import game.weapons.FurnaceEngine;
import game.effects.AttributeRestoring;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;

/**
 * SuspiciousTrader class, providing trade options to the player.
 * @author Liew Zhe Xing 32619472
 */
public class SuspiciousTrader extends Actor {
    private static final int HEALTH_POINTS = 10;      // Healing points per turn
    private static final int RESTORING_DURATION = 3;  // Duration for healing over time effect

    /**
     * Constructor for SuspiciousTrader
     */
    public SuspiciousTrader() {
        super("Suspicious Trader", 'ඞ', 1000);
        this.addCapability(Status.NON_ATTACKABLE);
    }

    /**
     * Trade items based on the type of remembrance provided by the player.
     * @param remembrance item given by player
     * @param player      player interacting with trader
     * @return a string indicating trade outcome
     */
    public String trade(Item remembrance, Player player) {
        if (player.hasItem(remembrance)) {
            player.removeItemFromInventory(remembrance);

            if (remembrance.hasCapability(Ability.TRADE_DIVINE_BEAST_HEAD)) {
                player.addItemToInventory(new DivineBeastHead());
                player.increaseMaxHealth(50);
                player.increaseMaxMana(100);
                return "You traded the Remembrance of the Dancing Lion for the Divine Beast Head!";
            } else if (remembrance.hasCapability(Ability.TRADE_FURNACE_ENGINE)) {
                player.addItemToInventory(new FurnaceEngine());
                player.addStatusEffect(new AttributeRestoring(BaseActorAttributes.HEALTH, RESTORING_DURATION, HEALTH_POINTS));
                return "You traded the Remembrance of the Furnace Golem for the Furnace Engine! Health will be restored over time.";
            } else {
                return "The trader cannot trade this item.";
            }
        } else {
            return "You don't have the required item for this trade.";
        }
    }

    /**
     * Override playTurn to handle trade interactions if the player is nearby.
     * @param actions    list of possible actions
     * @param lastAction last action taken
     * @param map        current game map
     * @param display    display for messages
     * @return Action to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location traderLocation = map.locationOf(this);

        for (Exit exit : traderLocation.getExits()) {
            Actor nearbyActor = exit.getDestination().getActor();

            // Check if the nearby actor is the player and add TradeAction if so
            if (nearbyActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new TradeAction((Player) nearbyActor, this);
            }
        }
        return new DoNothingAction();
    }
}
