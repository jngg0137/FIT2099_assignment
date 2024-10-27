package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.SuspiciousTrader;
import game.actors.Status;

/**
 * Class representing an action to attack
 * Note that the attacker must have a weapon, e.g.,
 * an intrinsic weapon or a weapon item.
 * Otherwise, the execute method will throw an error.
 * @author Adrian Kristanto
 * Modified by : Muhammad Hafiz Idlan Bin Hakimi
 *               Liew Zhe Xing 32619472
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Performs the attack action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the attack action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.hasCapability(Status.NON_ATTACKABLE)) {
            return target + " cannot be attacked.";
        }


        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Shows a description of the attack action that can be performed on the menu
     * @param actor The actor performing the action.
     * @return description of the attack action that can be performed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}

