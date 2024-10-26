package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public interface ReloadableItem {
    /**
     * Load ammunition into the item
     *
     * @param actor         the actor reloading the item
     * @param ammunition    ammunition to load into the item
     * @return              a description of what occurs when reloading
     */
    String load(Actor actor, Item ammunition);
}
