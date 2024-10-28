package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;
import game.effects.Poison;

import java.util.List;

/**
 * Class that represents the poison bolt item
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class PoisonBolt extends Ammunition{
    private final int damage = 80;
    private final int effectDuration = 20;
    private final Poison poison = new Poison(5,effectDuration);

    /**
     * Construct
     *
     */
    public PoisonBolt(){
        super("Poison bolt",'¥',true);
        this.addCapability(Ability.LOADABLE_BOLTS);
    }

    /**
     * Define what happens to the user, target or both when the target is hit
     *
     * @param attacker actor using this ammunition
     * @param target   actor being attacked
     * @param map      map this ammunition is used
     * @param hitTarget	should the target get hit
     * @return a description of what occurs when using this ammunition
     */
    @Override
    public String rangedEffect(Actor attacker, Actor target, GameMap map, boolean hitTarget) {
        List<Location> projectileLocations = super.calculateTrajectory(map.locationOf(attacker),
                map.locationOf(target));
        if (hitTarget && projectileLocations.contains(map.locationOf(target))) {
            target.hurt(damage);
            target.addStatusEffect(poison);
            return this + " hits " + target + " for " + damage +
                    " damage and poisons them for " + effectDuration + " turns.";
        }
        else
        {
            projectileLocations.getLast().addItem(this);
            return this + " fails to hit " + target + " and lands at " + projectileLocations.getLast();
        }
    }
}
