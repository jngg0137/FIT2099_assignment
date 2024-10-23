package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for ammunition used for weapons such as the crossbow
 *
 * @author Ng Jiunn Chieh 33590850
 */
public interface BoltAmmunition {
    /**
     * Define what happens to the user, target or both when the target is hit
     *
     * @param attacker  actor using this ammunition
     * @param target    actor being attacked
     * @param map       map this ammunition is used
     * @return          a description of what occurs when using this ammunition
     */
    String onHit(Actor attacker, Actor target, GameMap map);
}
