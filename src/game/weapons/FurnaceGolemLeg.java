package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Ability;
import game.effects.Explosion;
import game.grounds.Fire;

import java.util.Random;

/**
 * Represents a furnace golem enemy's leg
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class FurnaceGolemLeg extends IntrinsicWeapon {

    /**
     * Constructor
     */
    public FurnaceGolemLeg() {
        super(100, "stomps", 5);
    }

    /**
     * Has a chance to successfully hit the target with a stomp. No matter if the stomp hits the target or not,
     * there is a chance for an explosion to occur, in which the surrounding area gets set on fire.
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a description of the attack
     */
    public String attack(Actor attacker, Actor target, GameMap map) {
        String result = super.attack(attacker, target, map);
        Random rand = new Random();
        int chanceCreateExplosion = 10;

        Location attackerLocation = map.locationOf(attacker);

        if (rand.nextInt(100) < chanceCreateExplosion) {
            Explosion explosion = new Explosion(50, attackerLocation);
            explosion.explode();

            for (Exit exit : attackerLocation.getExits()) {
                Ground groundAffected = exit.getDestination().getGround();

                if (!groundAffected.hasCapability(Ability.IMMUNE_TO_BURNING)) {
                    exit.getDestination().setGround(new Fire(5, groundAffected));
                }
            }

            result += "\n" + attacker + " stomp attack results in shockwave in the surrounding areas";
        }

        return result;

    }
}