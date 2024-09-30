package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *
 *
 * @author
 * Modified by: Ng Jiunn Chieh 33590850
 */
public class LifeSteal implements WeaponArt {
    private static final int MANA_COST = 10;
    private static final int HEAL_AMOUNT = 20;

    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        attacker.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,HEAL_AMOUNT);
        attacker.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.DECREASE,MANA_COST);
        return "Lifesteal activated! Player healed for " + HEAL_AMOUNT + " HP.";
    }

    @Override
    public int getManaCost() {
        return MANA_COST;
    }


}
