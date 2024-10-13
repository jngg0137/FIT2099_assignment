package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Wind Divine Power.
 * This class implements the DivinePower interface and provides the functionality for a Wind Divine Power,
 * which allows an actor to blow a target to a random adjacent location and can switch to other divine powers
 * like Frost or Lightning based on a random chance.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class WindDivinePower implements DivinePower {

    private final Random random = new Random();
    private Display display = new Display();

    /**
     * Executes a special attack where the target is moved to a random adjacent location
     * on the game map.
     *
     * @param attacker the Actor who is attacking
     * @param target   the Actor who is being attacked
     * @param map      the GameMap the actors are on
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        // Move the target to a random adjacent location
        Location targetLocation = map.locationOf(target);
        List<Location> adjacentLocations = new ArrayList<>();
        for (Exit exit : targetLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(target)) {
                adjacentLocations.add(destination);
            }
        }
        if (!adjacentLocations.isEmpty()) {
            int index = random.nextInt(adjacentLocations.size()); // Use Random to get the index
            Location newLocation = adjacentLocations.get(index);
            map.moveActor(target, newLocation);
            display.println(target + " is blown by the wind to a new location!");
        }
    }

    /**
     * Determines the next divine power after using the Wind Divine Power.
     * There is a 30% chance that the next power will be Frost and a 70% chance it will be Lightning.
     *
     * @return the next DivinePower, which could be either FrostDivinePower or LightningDivinePower
     */
    @Override
    public DivinePower determineNextDivinePower() {
        int chance = random.nextInt(100); // Use Random to get a number between 0 and 99
        if (chance < 30) { // 30% chance to switch to Frost
            return new FrostDivinePower();
        } else { // 70% chance to switch to Lightning
            return new LightningDivinePower();
        }
    }

    /**
     * Gets the name of the Wind Divine Power.
     *
     * @return a {@link String} representing the name "Wind"
     */
    @Override
    public String getName() {
        return "Wind";
    }
}
