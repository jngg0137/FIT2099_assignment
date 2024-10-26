package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;
import game.actors.Ability;
import game.actors.FurnaceGolem;

public class FurnaceEngine extends WeaponItem {

    public FurnaceEngine() {
        super("Furnace Engine", 'E', 75, "stomps", 5, null);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // Get the location of the attacker (Tarnished player holding the Furnace Engine)
        Location attackerLocation = map.locationOf(attacker);

        // Trigger explosion and burning ground effects around the attacker’s location
        triggerExplosion(attackerLocation, map);
        return "Furnace Engine causes an explosion!";
    }

    private void triggerExplosion(Location location, GameMap map) {
        System.out.println("Explosion triggered at " + location);

        // Loop through all adjacent locations
        for (var exit : location.getExits()) {
            Location adjacentLocation = exit.getDestination();

            // Deal explosion damage to any actor nearby
            Actor adjacentActor = adjacentLocation.getActor();
            if (adjacentActor != null && !(adjacentActor instanceof FurnaceGolem)) {
                adjacentActor.hurt(50);
                System.out.println(adjacentActor + " takes 50 explosion damage!");
            }

            // Set burnable ground on fire
            if (!adjacentLocation.getGround().hasCapability(Ability.IMMUNE_TO_BURNING)) {
                adjacentLocation.setGround(new Fire(5, adjacentLocation.getGround()));
                System.out.println("The ground at " + adjacentLocation + " is now burning!");
            }
        }
    }
}
