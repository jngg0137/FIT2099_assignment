package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

/**
 * A base class that represents a consumable item
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public abstract class ConsumableItem extends Item implements Consumable {

    private int charges;
    private int maxCharges;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ConsumableItem(String name, char displayChar,int charges) {
        super(name, displayChar, true);
        this.charges = charges;
        maxCharges = charges;
    }

    /**
     * Affects the actor when consuming the item
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the item
     */
    @Override
    public abstract String consume(Actor actor,GameMap gameMap);

    /**
     * List of allowable actions that the item can perform to the current actor
     * @param owner the actor that owns the item
     * @return a collection of actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Obtains the charges left on the item
     * @return the amount of charges (left on the item
     */
    public int getCharges()
    {
        return charges;
    }

    /**
     * Decreases the charges of the item by 1 when consumed
     */
    public void decreaseCharges()
    {
        charges -= 1;
    }

    /**
     * Refreshes the charges to its maximum
     */
    public void refreshCharges()
    {
        charges = maxCharges;
    }

}
