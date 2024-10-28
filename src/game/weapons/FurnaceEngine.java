package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;


/**
 *  Represents the FurnaceEngine
 *  Tarnish is now able to use the FurnaceGolem's abilities after trading
 *
 * @author Liew Zhe Xing 32619472
 */

public class FurnaceEngine extends WeaponItem {
    Weapon furnaceGolemLeg = new FurnaceGolemLeg();

    public FurnaceEngine() {
        super("Furnace Engine", 'E', 75, "stomps", 5, null);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return furnaceGolemLeg.attack(attacker,target,map);
    }
}
