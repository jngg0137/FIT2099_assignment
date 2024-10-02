package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.AttributeRestoring;

/**
 * Represents a crimson tear item that can be consumed
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class CrimsonTear extends ConsumableItem{

    private static final int HEALTH_POINTS = 30;
    private static final int RESTORING_DURATION = 5;

    /***
     * Constructor for the CrimsonTear class.
     */
    public CrimsonTear() {

    super("Crimson Tear", '*', 1);
    }

    /**
     * Gives a restoring effect to the actor that consumes it, healing their health points by 30 points for 5 turns
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the item
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        this.decreaseCharges();
        actor.addStatusEffect(new AttributeRestoring(BaseActorAttributes.HEALTH,RESTORING_DURATION,HEALTH_POINTS));
        return String.format("%s consumed by %s. %s health will be restored by %d points for %d turns.",this, actor, actor, HEALTH_POINTS, RESTORING_DURATION);
    }
}
