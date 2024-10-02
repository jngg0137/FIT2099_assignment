package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a GateItem, which is a specific type of item that can have additional actions
 * related to gates.
 * The GateItem allows dynamic actions to be added and allows these actions to be performed
 * when the item is interacted with in a location.
 */
public class GateItem extends Item {
    /**
     * A list of actions that can be performed on or with the gate.
     */
    private ActionList gateActions = new ActionList();

    /**
     * Constructor for the GateItem.
     *
     * @param name The name of the GateItem.
     * @param displayChar The character used to display the GateItem on the map.
     * @param portable Whether the GateItem is portable (can be carried by the player).
     */
    public GateItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Adds a new action to the list of gate-related actions for this GateItem.
     *
     * @param newAction The action to be added to the gate's action list.
     */
    public void addGateAction(Action newAction) {
        this.gateActions.add(newAction);
    }

    /**
     * Overrides the allowableActions method to include the list of gate actions in addition to
     * the normal allowable actions for an item in a location.
     *
     * @param location The location where the GateItem is located.
     * @return An ActionList of allowable actions, including any gate-specific actions.
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(gateActions);
        return actions;
    }
}
