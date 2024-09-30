package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents the Poison status effect, which damages an actor over a specified duration.
 * The effect applies damage per tick and lasts for a set number of turns.
 * Once the duration ends, the poison effect is removed.
 *
 * <p>
 * The poison effect causes continuous damage to the actor each tick (turn) until the effect
 * duration expires or the actor is no longer conscious.
 * </p>
 *
 * @author Ng Jiunn Chieh
 */
public class Poison extends StatusEffect {
    private int damagePerTick;
    private int effectDuration;

    /**
     * Constructor for creating a Poison status effect.
     *
     * @param damagePerTick    The amount of damage applied per tick (turn).
     * @param effectDuration   The number of turns the poison effect will last.
     */
    public Poison(int damagePerTick, int effectDuration) {
        super("Poisoned");
        this.damagePerTick = damagePerTick;
        this.effectDuration = effectDuration;
    }

    /**
     * Applies the poison effect to the actor on each tick (turn).
     * If the poison effect is still active (duration is not zero), it will deal damage to the actor.
     * Once the duration ends, the poison effect is removed.
     *
     * @param location The location where the actor with the status effect is currently located.
     * @param actor    The actor holding the poison status effect.
     */
    @Override
    public void tick(Location location, Actor actor) {
        if (effectDuration != 0) {
            // Apply poison damage to the actor
            actor.hurt(damagePerTick);
            System.out.println(actor + " takes " + damagePerTick + " poison damage.");

            // Check if the actor is unconscious after the damage
            if (!actor.isConscious()) {
                actor.unconscious(location.map());
            }
        }
        else if (effectDuration == 0) {
            // Remove poison effect when duration expires
            actor.removeStatusEffect(this);
        }

        // Reduce the effect duration by 1 each tick
        effectDuration -= 1;
    }
}
