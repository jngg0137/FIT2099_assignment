package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *
 *
 * @author
 * Modified by: Liew Zhe Xing 32619472
 */
public class LifeSteal implements WeaponArt {
    private static final int MANA_COST = 10;
    private static final int HEAL_AMOUNT = 20;


    /**
     * the method use allows the users to use the Weapon Art "LifeSteal"
     * when attacker activates LifeSteal, it increases its Health Points by 20 but decreases Mana by 10
     * @param attacker the attacker(user that has the weapon)
     * @param target the target that is being attacked
     * @param map GameMap
     * @return String message based on the outcome of the attack
     * **/
    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        attacker.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,HEAL_AMOUNT);
        attacker.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.DECREASE,MANA_COST);
        return "Lifesteal activated! Player healed for " + HEAL_AMOUNT + " HP.";
    }



    /**
     * the method getManaCost allows users to get the information of the mana they have left.
     * @return MANA_COST
     * **/
    @Override
    public int getManaCost() {
        return MANA_COST;
    }


}
