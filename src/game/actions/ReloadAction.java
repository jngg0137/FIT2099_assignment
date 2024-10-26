package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ReloadableItem;

public class ReloadAction extends Action {
    private Item ammunition;
    private ReloadableItem reloadableItem;

    /**
     * Constructor
     *
     * @param reloadableItem    the item to be loaded with given ammunition
     * @param ammunition        the item that is being loaded
     */
    public ReloadAction(ReloadableItem reloadableItem, Item ammunition){
        this.reloadableItem = reloadableItem;
        this.ammunition = ammunition;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return reloadableItem.load(actor, ammunition);
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " reloads " + reloadableItem + " with " + ammunition + ".";
    }
}
