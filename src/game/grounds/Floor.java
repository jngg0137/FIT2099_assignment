package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Ability;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Muhammad Hafiz Idlan Bin Hakimi
 *
 */
public class Floor extends Ground {

    /**
     * Constructor
     */
    public Floor() {
        super('_', "Floor");
    }

    /**
     * Determines whether an actor can enter the floor
     * @param actor the Actor to check
     * @return true or false
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        return actor.hasCapability(Ability.ENTER_FLOOR);

    }
}
