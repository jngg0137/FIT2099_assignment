package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class that represents items that can be used as ammunition by other items
 *
 * @author Ng Jiunn Chieh 33590850
 */
public abstract class Ammunition extends Item {

    /**
     * Constructor
     *
     * @param name  the name of this Item
     * @param displayChar   the character to use to represent this item if it is on the ground
     * @param portable  true if and only if the Item can be picked up
     */
    public Ammunition(String name, char displayChar, boolean portable){
        super(name,displayChar,portable);
    }

    /**
     * Define what happens to the user, target or both when the target is hit by this item via ranged attack
     *
     * @param attacker  actor using this item
     * @param target    actor being attacked
     * @param map       map this item is used
     * @param hitTarget	was the target hit
     * @return          a description of what occurs when using this item
     */
    @Override
    public abstract String rangedEffect(Actor attacker, Actor target, GameMap map, boolean hitTarget);
}
