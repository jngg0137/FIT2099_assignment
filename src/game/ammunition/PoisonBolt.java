package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Poison;

public class PoisonBolt extends Item implements BoltAmmunition {
    private final int damage = 80;
    private final int effectDuration = 20;
    private final Poison poison = new Poison(5,effectDuration);

    /**
     * Construct
     *
     */
    public PoisonBolt(){
        super("Poison bolt",'¥',true);
    }

    /**
     * Define what happens to the user, target or both when the target is hit
     *
     * @param attacker actor using this ammunition
     * @param target   actor being attacked
     * @param map      map this ammunition is used
     * @return a description of what occurs when using this ammunition
     */
    @Override
    public String onHit(Actor attacker, Actor target, GameMap map) {
        target.hurt(damage);
        target.addStatusEffect(poison);
        return this + " hits " + target + " for " + damage +
                " damage and poisons them for " + effectDuration + "turns.";
    }
}
