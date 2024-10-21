package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundCapability;
import edu.monash.fit2099.engine.displays.Display;

import java.util.List;

/**
 * Represents the Frost Divine Power.
 * This divine power allows the user to perform a special attack on a target,
 * causing the target to slip and drop all their inventory items if they are
 * standing on a ground with the WATER capability. The next divine power in sequence is Wind.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class FrostDivinePower implements DivinePower {

    /**
     * A display instance for outputting messages to the console.
     */
    private Display display = new Display();

    /**
     * Executes the special attack of Frost Divine Power.
     * If the target is standing on a ground with the WATER capability,
     * they will slip and drop all of their inventory items.
     * Otherwise, the attack has no effect.
     *
     * @param attacker the Actor performing the attack
     * @param target   the Actor being attacked
     * @param map      the GameMap in which the attack occurs
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        // Check if the target is standing on a ground with WATER capability
        Location targetLocation = map.locationOf(target);
        if (targetLocation.getGround().hasCapability(GroundCapability.WATER)) {
            // Target slips and drops all inventory items
            List<Item> inventory = target.getItemInventory();
            for (Item item : inventory) {
                targetLocation.addItem(item);
            }
            inventory.clear();
            display.println(target + " slips on the ice and drops all their items!");
        } else {
            display.println("Frost divine power has no effect as " + target + " is not on a body of water.");
        }
    }

    /**
     * Determines the next divine power in sequence.
     * Frost Divine Power always switches to Wind Divine Power after use.
     *
     * @return a new instance of {@link WindDivinePower}
     */
    @Override
    public DivinePower determineNextDivinePower() {
        // Always switch to Wind
        return new WindDivinePower();
    }

    /**
     * Returns the name of the Frost Divine Power.
     *
     * @return a String representing the name of the divine power
     */
    @Override
    public String getName() {
        return "Frost";
    }
}
