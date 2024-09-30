package game;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *
 *
 * @author
 * Modified by: Ng Jiunn Chieh 33590850
 */
public interface WeaponArt {
    String use(Actor attacker, Actor target, GameMap map);
    int getManaCost();
}