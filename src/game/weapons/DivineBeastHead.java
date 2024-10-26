package game.weapons;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DivineBeastHead extends WeaponItem {
    public DivineBeastHead() {
        super("Divine Beast Head", '$', 150, "bites", 30, null);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // Use abilities from the Divine Beast Dancing Lion
        // Apply three divine powers here
        return "Divine Beast Head unleashes its powers!";
    }
}
