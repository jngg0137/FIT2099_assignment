package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Ability;

/**
 * Represents the Remembrance dropped by Divine Beast Dancing Lion.
 * @author Liew Zhe Xing 32619472
 */
public class RemembranceOfDancingLion extends Item {
    public RemembranceOfDancingLion() {
        super("Remembrance of the Dancing Lion", '*', true);
        this.addCapability(Ability.TRADE_ITEM);
        this.addCapability(Ability.TRADE_DIVINE_BEAST_HEAD);
    }
}
