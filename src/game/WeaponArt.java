package game;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.Weapon;
public interface WeaponArt {
    String use(Actor attacker, Actor target, WeaponItem weapon);
}
