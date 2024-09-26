package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.effects.Poison;

import java.util.Random;

public class Stinger extends IntrinsicWeapon {
    final int poisonChance = 30;
    final Poison poison;
    /**
     * Constructor
     *
     */
    public Stinger() {
        super(20, "stings", 25);
        this.poison = new Poison(10,2);
    }

    /**
     * Attack method of the intrinsic weapon.
     * If the hit rate is not met, the attacker misses the target.
     * Otherwise, the target is hit, hurting the target by the intrinsic weapon's damage
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return the description once the attack is done
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();

        //normal attack
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }
        target.hurt(damage);

        String ret = String.format("%s %s %s for %d damage", attacker, verb, target, damage);

        //chance for the attack to poison the target
        if ((rand.nextInt(100) < poisonChance)) {
            attacker.addStatusEffect(poison);
            ret += "\n";
            ret += attacker + " poisons " + target + ".";
        }

        return ret;
    }
}
