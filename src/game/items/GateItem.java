package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class GateItem extends Item {
    private Action gateAction;

    public GateItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void addGateAction(Action newAction) {
        this.gateAction = newAction;
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(gateAction);
        return actions;
    }
}
