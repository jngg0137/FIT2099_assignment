package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Divine Power.
 *
 * @author Mohamad Kassem Alsabbagh 34640843
 */
public interface DivinePower {

    /**
     * Executes the special attack associated with this divine power.
     *
     * @param attacker the actor performing the attack
     * @param target   the target actor
     * @param map      the game map
     */
    void executeSpecialAttack(Actor attacker, Actor target, GameMap map);

    /**
     * Determines the next divine power based on transition probabilities.
     *
     * @return the next DivinePower instance
     */
    DivinePower determineNextDivinePower();

    /**
     * Gets the name of the divine power.
     *
     * @return the name as a String
     */
    String getName();
}
