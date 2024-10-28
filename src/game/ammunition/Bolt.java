package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;

import java.util.List;

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
     * @param hitTarget	should the target get hit
     * @return a description of what occurs when using this ammunition
     */
    @Override
    public String rangedEffect(Actor attacker, Actor target, GameMap map, boolean hitTarget) {
        List<Location> projectileLocations = super.calculateTrajectory(map.locationOf(attacker),
                map.locationOf(target));
        if (hitTarget && projectileLocations.contains(map.locationOf(target))) {
            target.hurt(damage);
            return this + " hits " + target + " for " + damage + " damage.";
        }
        else
        {
            projectileLocations.getLast().addItem(this);
            return this + " fails to hit " + target + " and lands at " + projectileLocations.getLast();
        }
    }
}
