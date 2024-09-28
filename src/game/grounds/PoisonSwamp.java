package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.effects.Poison;

public class PoisonSwamp extends Ground {

    public PoisonSwamp() {
        super('+', "Poison Swamp");
    }

    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (actor != null && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // Apply poison effect
            actor.addStatusEffect(new Poison(5, 3));
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true; // Actors can traverse poison swamp
    }

}
