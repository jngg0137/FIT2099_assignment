package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actors.Ability;
import game.weaponarts.WeaponArt;

import java.util.Random;

/**
 * Class representing items that can be used as a ranged weapon
 *
 */
public abstract class RangedWeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int hitRate;
    private int damage;
    private final String verb;
    private WeaponArt weaponArt;
    private float damageMultiplier;
    private final int range;

    /**
     * Constructor
     *
     * @param name          name of the item
     * @param displayChar   character to use for display when item is on the ground
     * @param damage        amount of damage this weapon does
     * @param verb          verb to use for this weapon
     * @param hitRate       the probability/chance to hit the target
     * @param weaponArt     Weapon art to give this weapon
     * @param range         weapon range (e.g. a range of 3 will allow for attacking within a 3x3 grid
     */
    public RangedWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, WeaponArt weaponArt, int range){
        super(name, displayChar, true);
        this.damage = damage;
        this.hitRate = hitRate;
        this.verb = verb;
        this.weaponArt = weaponArt;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.range = range;
        this.addCapability(Ability.RANGED);
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
        String ret = "";
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        // Check if weapon art is present
        if (weaponArt != null) {
            // If player has enough mana, use weapon art
            if (attacker.getAttribute(BaseActorAttributes.MANA) >= weaponArt.getManaCost()) {
                ret += weaponArt.use(attacker, target, map);
            }
            else
            {
                ret += "Not enough mana available to trigger weapon art!";
            }
        }

        // Perform normal attack
        target.hurt(Math.round(damage * damageMultiplier));

        ret += String.format("\n%s %s %s for %d damage", attacker, verb, target, damage);
        return ret;
    }

    /**
     * List of allowable actions that the actor can perform with the ranged item
     *
     * @param owner     actor that has this item in inventory
     * @param location  location of actor that has this item in inventory
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableRangedActions(Actor owner, Location location){
        ActionList actions = super.allowableActions(owner, location);
        for (Location suroundingLocation : location.getSurroundingLocations(range*2)){
            if (suroundingLocation.containsAnActor()){
                actions.add(new AttackAction(suroundingLocation.getActor(),suroundingLocation.toString(),this));
            }
        }

        return actions;
    }
}
