package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for ammunition used for weapons such as the crossbow
 *
 * @author Ng Jiunn Chieh 33590850
 */
public interface Ammunition{
    /**
     * Define what happens to the user, target or both when using this ammunition
     *
     * @param attacker  actor using this ammunition
     * @param target    actor being attacked
     * @param map       map this ammunition is used
     * @return          a description of what occurs when using this ammunition
     */
    String use(Actor attacker, Actor target, GameMap map);
}
