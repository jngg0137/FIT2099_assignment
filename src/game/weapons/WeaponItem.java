package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * @author Muhammad Haifz Idlan Bin Hakimi
 */
public abstract class WeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;
    private int strengthRequirement;

    /**
     * Constructor.
     *
     * @param name                 name of the item
     * @param displayChar          character to use for display when item is on the ground
     * @param damage               amount of damage this weapon does
     * @param verb                 verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate              the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    /**
     * Performs an attack to a target, has a chance to land a hit successfully
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a description of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return a list of allowable actions
     */
    public ActionList allowableActions(Actor otherActor, Location location)
    {
        ActionList actions = super.allowableActions(otherActor,location);
        actions.add(new AttackAction(otherActor, location.toString(),this));
        return actions;
    }

}
