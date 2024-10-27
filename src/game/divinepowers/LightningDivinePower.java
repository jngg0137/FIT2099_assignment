package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundCapability;
import game.grounds.LightningPool;
import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Lightning Divine Power.
 * This power allows the attacker to strike all actors in the surrounding locations with lightning,
 * turning the ground into a LightningPool and applying damage. If the target is on a water surface,
 * damage is doubled.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class LightningDivinePower implements DivinePower {

    private final Random random = new Random();
    private Display display = new Display();

    /**
     * Executes a special attack that strikes all actors in the attacker's surrounding locations with lightning.
     * If the target is on a ground with the WATER capability, the damage is doubled.
     *
     * @param attacker the actor performing the attack
     * @param target   the target of the attack
     * @param map      the game map
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        Location attackerLocation = map.locationOf(attacker);

        // Create LightningPool in surrounding locations
        List<Location> surroundingLocations = new ArrayList<>();
        surroundingLocations.add(attackerLocation);
        for (Exit exit : attackerLocation.getExits()) {
            surroundingLocations.add(exit.getDestination());
        }

        for (Location loc : surroundingLocations) {
            // Replace the ground with LightningPool
            loc.setGround(new LightningPool());
            // Damage any actors on the location
            Actor actorAtLocation = loc.getActor();
            if (actorAtLocation != null) {
                int damage = 50;
                // Double damage if the ground has WATER capability
                if (loc.getGround().hasCapability(GroundCapability.WATER) && loc.equals(map.locationOf(target))) {
                    damage *= 2;
                }
                actorAtLocation.hurt(damage);
                display.println(actorAtLocation + " is struck by lightning for " + damage + " damage!");
                if (!actorAtLocation.isConscious()) {
                    actorAtLocation.unconscious(map);
                }
            }
        }
    }

    /**
     * Determines the next divine power. There is a 40% chance to switch to Frost, 40% chance to switch to Wind,
     * and a 20% chance to stay with Lightning.
     *
     * @return the next DivinePower
     */
    @Override
    public DivinePower determineNextDivinePower() {
        int chance = random.nextInt(100);
        if (chance < 40) { // 40% chance to switch to Frost
            return new FrostDivinePower();
        } else if (chance < 80) { // Next 40% chance to switch to Wind
            return new WindDivinePower();
        } else { // Remaining 20% chance to stay on Lightning
            return this;
        }
    }

    /**
     * Returns the name of this divine power.
     *
     * @return the name "Lightning"
     */
    @Override
    public String getName() {
        return "Lightning";
    }
}
