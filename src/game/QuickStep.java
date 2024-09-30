package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 *
 * @author
 * Modifed by: Ng Jiunn Chieh 33590850
 */
public class QuickStep implements WeaponArt {
    private static final int MANA_COST = 0;
    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        Random random = new Random();

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

        if (validExits.isEmpty()) {
            return "Quickstep activated, but no valid spaces to move to.";
        }

        Location moveTo = validExits.get(random.nextInt(validExits.size())).getDestination();
        map.moveActor(attacker,moveTo);
        return "Quickstep activated! Player moved to " + moveTo.toString() + " after attacking.";
    }

    @Override
    public int getManaCost() {
        return MANA_COST;
    }
}

