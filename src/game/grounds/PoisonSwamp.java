package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.effects.Poison;

/**
 * Represents a PoisonSwamp in the game, a type of ground that poisons actors standing on it.
 * Actors with a specific status are affected by the poison when they are in this location.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class PoisonSwamp extends Ground {

    /**
     * Constructor for PoisonSwamp, initializes with a display character and description.
     */
    public PoisonSwamp() {
        super('+', "Poison Swamp");
    }

    /**
     * This method is called once per turn, simulating the passage of time in the location.
     * If an actor is present at the location and has the status {@code Status.HOSTILE_TO_ENEMY},
     * the poison effect is applied.
     *
     * @param location The location of the PoisonSwamp in the game world.
     */
    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (actor != null && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // Apply poison effect to the actor
            actor.addStatusEffect(new Poison(5, 3)); // Poison effect with 5 damage over 3 turns
        }
    }

    /**
     * Determines whether an actor can enter the PoisonSwamp. In this case, all actors can traverse
     * the PoisonSwamp, regardless of their capabilities.
     *
     * @param actor The actor attempting to enter the PoisonSwamp.
     * @return {@code true}, meaning the actor can enter the PoisonSwamp.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true; // All actors can enter the poison swamp
    }
}
