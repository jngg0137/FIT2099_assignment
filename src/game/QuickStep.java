package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import java.util.Random;
import game.weapons.WeaponItem


public class QuickStep implements WeaponArt {
    @Override
    public String use(Actor attacker, Actor target, WeaponItem weapon) {
        // Deal damage to the target
        target.hurt(weapon.getDamage());

        // Move attacker to a random adjacent position
        GameMap map = // retrieve the map here;
                Location currentLocation = map.locationOf(attacker);
        List<Location> adjacentLocations = currentLocation.getExits();
        Location randomLocation = adjacentLocations.get(new Random().nextInt(adjacentLocations.size()));

        map.moveActor(attacker, randomLocation);

        return "Quickstep activated! Player moved to a new location after attacking.";
    }
}

