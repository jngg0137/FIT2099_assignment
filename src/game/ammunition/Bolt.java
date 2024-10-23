package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class which represents the base ammunition for the crossbow
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class Bolt extends Item implements Ammunition{
    private final int damage = 100;
    private final int knockback = 2;

    /**
     * Constructor
     *
     */
    public Bolt(){
        super("Bolt",'y',true);
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
        //for now doesn't push enemies back
        return this + " hits " + target + " for " + damage +
                " and pushed them back " + knockback + " tiles.";
    }
}
