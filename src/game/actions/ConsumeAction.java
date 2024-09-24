package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.Consumable;

/**
 * Represents an action to consume
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class ConsumeAction extends Action {

    private Consumable consumable;

    /**
     * Constructor
     * @param consumable the consumable to be consumed
     */
    public ConsumeAction(Consumable consumable)
    {
        this.consumable = consumable;
    }

    /**
     * Performs the consume action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the consume action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return consumable.consume(actor,map);
    }

    /**
     * Shows a description of the consume action that can be performed on the menu
     * @param actor The actor performing the action.
     * @return description of the consume action that can be performed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
