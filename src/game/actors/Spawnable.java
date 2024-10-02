package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for actors that can be spawned from a spawner
 *
 * @author Ng Jiunn Chieh 33590850
 */
public interface Spawnable {
    /**
     * Method that returns new object of this actor
     *
     * @return  new object of this actor
     */
    Actor newActor();
}
