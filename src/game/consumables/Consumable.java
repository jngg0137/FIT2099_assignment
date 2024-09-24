package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for consumables
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public interface Consumable {

    /**
     * Defines what may occur to the actor once they consume the consumable
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming
     */
    String consume(Actor actor, GameMap gameMap);
}
