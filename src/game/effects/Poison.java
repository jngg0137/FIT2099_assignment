package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;

/**
 * Class which stores details on poison effect
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class Poison extends StatusEffect {
    private int damagePerTick;
    private int effectDuration;

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
        if (!actor.hasCapability(Ability.IMMUNE_TO_POISON) && effectDuration != 0) {
            actor.hurt(damagePerTick);
            System.out.println(actor + " takes " + damagePerTick + " poison damage.");
            if (!actor.isConscious()) {
                actor.unconscious(location.map());
            }
        }
        else if (effectDuration == 0)
        {
            actor.removeStatusEffect(this);
        }
        effectDuration -= 1;
    }
}
