package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Ability;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Muhammad Hafiz Idlan Bin Hakimi (33589690)
 *
 */
public class Puddle extends Ground {
    /**
     * Constructor
     */
    public Puddle() {
        super('~', "Puddle");
        this.addCapability(Ability.IMMUNE_TO_BURNING);
    }
}
