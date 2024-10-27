package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;

/**
 * Class which represents the base ammunition for the crossbow
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class Bolt extends Ammunition{
    private final int damage = 100;
    private final int knockback = 2;

    /**
     * Constructor
     *
     */
    public Bolt(){
        super("Bolt",'y',true);
        this.addCapability(Ability.LOADABLE_BOLTS);
    }

    /**
     * Define what happens to the user, target or both when the target is hit
     *
     * @param attacker actor using this ammunition
     * @param target   actor being attacked
     * @param map      map this ammunition is used
     * @param hitTarget	was the target hit
     * @return a description of what occurs when using this ammunition
     */
    @Override
    public String rangedEffect(Actor attacker, Actor target, GameMap map, boolean hitTarget) {
        if (hitTarget) {
            target.hurt(damage);
            //for now doesn't push enemies back
            return this + " hits " + target + " for " + damage +
                    " and pushed them back " + knockback + " tiles.";
        }
        else
        {
            Location projectileLocation  = super.calculateTrajectory(map.locationOf(attacker),map.locationOf(target));
            projectileLocation.addItem(this);
            return this + " misses " + target + " and lands at " + projectileLocation;
        }
    }
}
