package game.weapons;

import game.actors.Ability;
import game.weaponarts.WeaponArt;


public class Crossbow extends RangedWeaponItem{
    /**
     * Constructor
     *
     */
    public Crossbow(String name, char displayChar, int damage, String verb, int hitRate, WeaponArt weaponArt){
        super(name,displayChar,damage,verb,hitRate,weaponArt);
        this.addCapability(Ability.USE_BOLTS);
    }
}
