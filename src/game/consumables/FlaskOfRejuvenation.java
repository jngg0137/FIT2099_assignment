package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents a flask of rejuvenation item that can be consumed
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class FlaskOfRejuvenation extends ConsumableItem {

    private int manaPoints = 100;
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param charges
     */
    public FlaskOfRejuvenation(String name, char displayChar, int charges) {
    super(name, displayChar, charges);
    }

    /**
     * Restores the actor's mana after consuming
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the item
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        int chargesAvailable = getCharges();

        if (chargesAvailable > 0)
        {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaPoints);
            decreaseCharges();
        }
        else
        {
            return this + " is empty";
        }

        return String.format("%s consumed by %s. %s replenishes mana for %d points",this,actor, actor, manaPoints);
    }
}
