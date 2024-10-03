package game.effects;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;

/**
 * Represents a burning effect that can be given to an actor
 * @author Muhammad Hafiz Idlan Bin Hakimi
 * Modified by: Ng Jiunn Chieh 33590850
 */
public class Burning extends StatusEffect {

    private Display display = new Display();
    private int damageOneTick;
    private int effectDuration;
    private final GameEntity causeOfBurning;
    private final int endEffectDuration = 0;

    /**
     * Constructor
     * @param damageOneTick the damage per tick the burning effect causes
     * @param effectDuration how long the effect lingers
     * @param causeOfBurning what causes the burning effect
     */
    public Burning(int damageOneTick,int effectDuration, GameEntity causeOfBurning) {
        super("Burning");
        this.damageOneTick = damageOneTick;
        this.effectDuration = effectDuration;
        this.causeOfBurning = causeOfBurning;
    }

    /**
     * Burns the actor until the effect wears off
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {

        if (!actor.hasCapability(Ability.IMMUNE_TO_BURNING) && effectDuration != endEffectDuration)
        {
            actor.hurt(damageOneTick);
            display.println(causeOfBurning + " burns " + actor);
            if (!actor.isConscious())
            {
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
