package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents an explosion that may damage anything within its vicinity
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class Explosion {

    private final Location startingPoint;
    private int damage;

    /**
     * Constructor
     * @param damage the damage dealt by the explosion
     * @param startingPoint the center location of the explosion
     */
    public Explosion(int damage,Location startingPoint)
    {
        this.damage = damage;
        this.startingPoint = startingPoint;
    }

    /**
     * Causes an explosion, damaging any actor that is caught within the radius
     */
    public void explode(){

        for (Exit exit : startingPoint.getExits())
        {
            Location locationExploded = exit.getDestination();
            if (exit.getDestination().containsAnActor())
            {
                Actor actorAffected = locationExploded.getActor();
                actorAffected.hurt(damage);
                if (!actorAffected.isConscious())
                {
                    actorAffected.unconscious(locationExploded.map());
                }
            }
        }

    }
}
