package game.weapons;

import game.actors.Ability;
import game.weaponarts.WeaponArt;


public class Crossbow extends RangedWeaponItem{
    /**
     * Constructor
     *
     * @param name          name of the item
     * @param displayChar   character to use for display when item is on the ground
     * @param damage        amount of damage this weapon does
     * @param verb          verb to use for this weapon
     * @param hitRate       the probability/chance to hit the target
     * @param weaponArt     Weapon art to give this weapon
     */
    public Crossbow(String name, char displayChar, int damage, String verb, int hitRate, WeaponArt weaponArt, int range){
        super(name,displayChar,damage,verb,hitRate,weaponArt,range);
        this.addCapability(Ability.USE_BOLTS);
    }
}
