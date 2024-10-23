package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;
import game.grounds.Fire;

public class BurningBolt extends Item implements Ammunition{
    private final int damage = 80;

    /**
     * Constructor
     *
     */
    public BurningBolt(){
        super("Burning bolt",'Ý',true);
    }

    /**
     * Define what happens to the user, target or both when using this ammunition
     *
     * @param attacker actor using this ammunition
     * @param target   actor being attacked
     * @param map      map this ammunition is used
     * @return a description of what occurs when using this ammunition
     */
    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        Location targetLocation = map.locationOf(target);

        for (Exit exit : targetLocation.getExits()){
            Ground groundAffected = exit.getDestination().getGround();

            if (!groundAffected.hasCapability(Ability.IMMUNE_TO_BURNING)) {
                exit.getDestination().setGround(new Fire(40, groundAffected));
            }
        }

        return this + " hits " + target + " for " + damage +
                " and sets nearby tiles on fire.";
    }
}
