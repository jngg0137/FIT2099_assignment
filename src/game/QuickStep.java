package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.weapons.WeaponItem;


public class QuickStep implements WeaponArt {

    private Actor target;
    @Override
    public String use(Actor attacker, Actor actor, WeaponItem weapon, GameMap map) {
        // Deal damage to the target
        target.hurt(weapon.getDamage());

        // Move attacker to a random adjacent position

        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        List<Exit> validExits = new ArrayList<>();

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                validExits.add(exit);
            }
        }


        return "Quickstep activated! Player moved to a new location after attacking.";
    }
}

