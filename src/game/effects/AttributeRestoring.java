package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a restoring effect that can be given to an actor
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class AttributeRestoring extends StatusEffect {

    private final Enum<?> attributeToBeRestored;
    private int effectDuration;
    private final int endEffectDuration = 0;
    private final int restoredPointsPerTick;

    /**
     * Constructor for the AttributeRestoring claass
     * @param attributeToBeRestored the attribute of the actor that is being restored
     * @param effectDuration how long the effect lingers
     * @param restoredPointsPerTick the amount of points the attribute is restored by per tick
     */
    public AttributeRestoring(Enum<?> attributeToBeRestored, int effectDuration, int restoredPointsPerTick) {
        super(attributeToBeRestored + "Restored");
        this.attributeToBeRestored = attributeToBeRestored;
        this.effectDuration = effectDuration;
        this.restoredPointsPerTick = restoredPointsPerTick;
    }

    /**
     * Restores the actor until the effect wears off
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {

        if (effectDuration != endEffectDuration)
        {
            if (attributeToBeRestored != BaseActorAttributes.HEALTH)
            {
                actor.modifyAttribute(attributeToBeRestored, ActorAttributeOperations.INCREASE,restoredPointsPerTick);
            }
            else{
                actor.heal(restoredPointsPerTick);
            }
        }
        else if (effectDuration == endEffectDuration)
        {
            actor.removeStatusEffect(this);
        }

        effectDuration -= 1;

    }

}