package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.divinepowers.DivinePower;
import game.divinepowers.FrostDivinePower;
import game.divinepowers.LightningDivinePower;
import game.divinepowers.WindDivinePower;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Divine Beast Head, allowing the player to access three divine powers: Frost, Lightning, and Wind.
 */
public class DivineBeastHead extends WeaponItem {

    // Enum for selecting divine powers
    public enum DivinePowers { FROST, LIGHTNING, WIND }

    private final Map<DivinePowers, DivinePower> divinePowerMap;

    public DivineBeastHead() {
        super("Divine Beast Head", '$', 150, "bites", 30, null);

        // Map divine powers to instances of DivinePower
        divinePowerMap = new HashMap<>();
        divinePowerMap.put(DivinePowers.FROST, new FrostDivinePower());
        divinePowerMap.put(DivinePowers.LIGHTNING, new LightningDivinePower());
        divinePowerMap.put(DivinePowers.WIND, new WindDivinePower());
    }

    /**
     * Executes the selected divine power's special attack.
     *
     * @param attacker The actor using the DivineBeastHead.
     * @param target   The target of the attack.
     * @param map      The current game map.
     * @param power    The selected divine power.
     * @return Description of the special attack.
     */
    public String executeDivinePower(Actor attacker, Actor target, GameMap map, DivinePowers power) {
        DivinePower chosenPower = divinePowerMap.get(power);

        if (chosenPower != null) {
            chosenPower.executeSpecialAttack(attacker, target, map);
            return String.format("Divine Beast Head unleashes the %s power!", power);
        }
        return "Invalid power selection.";
    }
}
