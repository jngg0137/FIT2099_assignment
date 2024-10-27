package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a LightningPool on the ground.
 * Damages any actor that stands on it.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public class LightningPool extends Ground {

    private static final int DAMAGE = 50;
    private Display display = new Display();

    /**
     * Constructor for LightningPool.
     */
    public LightningPool() {
        super('⚡', "Lightning Pool");
    }

    /**
     * Called once per turn to handle the passage of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null) {
            actor.hurt(DAMAGE);
            display.println(actor + " takes " + DAMAGE + " damage from the lightning pool!");
            if (!actor.isConscious()) {
                actor.unconscious(location.map());
            }
        }
    }

    /**
     * Determines whether an Actor can enter this location.
     *
     * @param actor the Actor attempting to enter
     * @return true, allowing any actor to enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
