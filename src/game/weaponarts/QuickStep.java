package game.weaponarts;

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
 * Modifed by: Liew Zhe Xing 32619472
 */

public class QuickStep implements WeaponArt {
    private static final int MANA_COST = 0;

    /**
     * This method "use" is for the weapon to implement the Weapon Art "QuickStep"
     * firstly, it checks if the attacker and target is on the map or not
     * then checks if the user has space to Quickstep after attacking, if it doesnt, a message
     * saying the user does not have space to move to displays. if it does, user will quickstep into
     * position and a message will display letting the user know it has successfully activated quickstep.
     * @param attacker the attacker(user that has the weapon)
     * @param target the target that is being attacked
     * @param map GameMap
     * @return String message based on the outcome of the attack
     * **/
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


    /**
     * the method getManaCost allows users to get the information of the mana they have left.
     * @return MANA_COST
     * **/
    @Override
    public int getManaCost() {
        return MANA_COST;
    }
}

