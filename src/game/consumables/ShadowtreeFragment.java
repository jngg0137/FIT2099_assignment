package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ExtraActorAttributes;

/**
 * Represents a shadow tree fragment item that can be consumed
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class ShadowtreeFragment extends ConsumableItem {

    private static final int HEALTH_POINTS = 50;
    private static final int MANA_POINTS = 25;
    private static final int STRENGTH_POINTS = 5;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ShadowtreeFragment(String name, char displayChar) {
    super(name, displayChar, 1);
    }

    /**
     * Increases the maximum health, mana and strength of the actor by a certain amount.
     * Also heals and restores the actor's health and mana by the same amount the maximum health and mana increases
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the item
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        int currentHealth = actor.getAttribute(BaseActorAttributes.HEALTH);
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH,ActorAttributeOperations.INCREASE,HEALTH_POINTS);
        actor.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,currentHealth);
        actor.heal(HEALTH_POINTS);

        int currentMana = actor.getAttribute(BaseActorAttributes.MANA);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA,ActorAttributeOperations.INCREASE,MANA_POINTS);
        actor.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.UPDATE,currentMana);
        actor.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.INCREASE,MANA_POINTS);

        actor.modifyAttributeMaximum(ExtraActorAttributes.STRENGTH,ActorAttributeOperations.INCREASE,STRENGTH_POINTS);

        decreaseCharges();
        actor.removeItemFromInventory(this);
        return String.format("%s consumed by %s. %s maximum health,mana and strength increases by %d,%d, and %d points!",this,actor, actor, HEALTH_POINTS,MANA_POINTS,STRENGTH_POINTS);
    }
}
