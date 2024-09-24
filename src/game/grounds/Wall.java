package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Muhammad Hafiz Idlan Bin Hakimi
 *
 */
public class Wall extends Ground {

    /**
     * Constructor
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * Prevents any actor to enter a wall
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
