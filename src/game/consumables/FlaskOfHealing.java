package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents a flask of healing item that can be consumed.
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class FlaskOfHealing extends ConsumableItem {

    private int healthPoints = 150;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param charges
     */
    public FlaskOfHealing(String name, char displayChar, int charges) {
    super(name, displayChar,charges);
    }

    /**
     * Heals the actor by a certain amount after consuming
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the item
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        int chargesAvailable = getCharges();

        if (chargesAvailable > 0)
        {
            actor.heal(healthPoints);
            decreaseCharges();
        }
        else
        {
            return this + " is empty";
        }

        return String.format("%s consumed by %s. %s heals for %d points",this,actor, actor, healthPoints);
    }

}
