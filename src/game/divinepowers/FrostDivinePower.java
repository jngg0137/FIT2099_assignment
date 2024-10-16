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
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class FrostDivinePower implements DivinePower {

    private Display display = new Display();

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

    @Override
    public DivinePower determineNextDivinePower() {
        // Always switch to Wind
        return new WindDivinePower();
    }

    @Override
    public String getName() {
        return "Frost";
    }
}
