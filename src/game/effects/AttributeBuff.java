package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a buff effect that can be given to an actor
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class AttributeBuff extends StatusEffect {

    private final Actor actorBuffed;
    private final Enum<?> attributeToBeBuffed;
    private int effectDuration;
    private final int endEffectDuration = 0;
    private final int pointsBuffed;

    /**
     * Constructor for the AttributeBuff class
     * @param actorBuffed the actor receiving the buff
     * @param attributeToBeBuffed the attribute of the actor that is being buffed
     * @param effectDuration how long the effect lingers
     * @param pointsBuffed the amount of points the attribute will be increased by
     */
    public AttributeBuff(Actor actorBuffed, Enum<?> attributeToBeBuffed, int effectDuration, int pointsBuffed) {
        super(attributeToBeBuffed + "Buff");
        this.actorBuffed = actorBuffed;
        this.attributeToBeBuffed = attributeToBeBuffed;
        this.effectDuration = effectDuration;
        this.pointsBuffed = pointsBuffed;

        actorBuffed.modifyAttributeMaximum(attributeToBeBuffed, ActorAttributeOperations.INCREASE, pointsBuffed);
    }

    /**
     * Buffs the actor until the effect wears off
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {

        if (effectDuration == endEffectDuration) {
            actor.removeStatusEffect(this);
            actor.modifyAttributeMaximum(attributeToBeBuffed, ActorAttributeOperations.DECREASE, pointsBuffed);
        }

        effectDuration -= 1;
    }
}