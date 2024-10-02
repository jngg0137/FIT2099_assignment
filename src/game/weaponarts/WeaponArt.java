package game.weaponarts;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *
 *
 * @author
 * Modified by: Liew Zhe Xing 32619472
 */


public interface WeaponArt {

    /**
     * Overriden method for LifeSteal and QuickStep
     * **/
    String use(Actor attacker, Actor target, GameMap map);
    int getManaCost();
}