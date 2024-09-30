package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;

import java.util.ArrayList;
import game.weapons.WeaponItem;


public class QuickStep implements WeaponArt {
    private static final int MANA_COST = 0;
    @Override
    public String use(Actor attacker, Actor target, WeaponItem weapon, GameMap map) {
        if(!map.contains(attacker) || !map.contains(target))
            return null;

        Location here = map.locationOf(attacker);
        ArrayList<Exit> validExits = new ArrayList<>();

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(attacker) && !destination.containsAnActor()) {
                validExits.add(exit);
            }
        }

        return "Quickstep activated! Player moved to a new location after attacking.";
    }

    @Override
    public int getManaCost() {
        return MANA_COST;
    }
}

