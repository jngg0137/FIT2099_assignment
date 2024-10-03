package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;

/**
 * Represents the Poison status effect, which damages an actor over a specified duration.
 * The effect applies damage per tick and lasts for a set number of turns.
 * Once the duration ends, the poison effect is removed.
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class Poison extends StatusEffect {
    private Display display = new Display();
    private int damagePerTick;
    private int effectDuration;
    private final int endEffectDuration = 0;

    /**
     * Constructor for Poison status effect
     *
     * @param damagePerTick     damage per tick the poison effect causes
     * @param effectDuration    how long the effect lingers
     */
    public Poison(int damagePerTick,int effectDuration) {
        super("Poisoned");
        this.damagePerTick = damagePerTick;
        this.effectDuration = effectDuration;
    }

    /**
     * Poison the actor until the effect wears off
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor){
        if (!actor.hasCapability(Ability.IMMUNE_TO_POISON) && effectDuration != endEffectDuration) {
            actor.hurt(damagePerTick);
            display.println(actor + " takes " + damagePerTick + " poison damage.");
            if (!actor.isConscious()) {
                actor.unconscious(location.map());
            }
        }
        else if (effectDuration == endEffectDuration)
        {
            actor.removeStatusEffect(this);
        }
        effectDuration -= 1;
    }
}
