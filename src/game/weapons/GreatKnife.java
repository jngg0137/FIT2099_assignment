package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.weaponarts.WeaponArt;
import game.actors.ExtraActorAttributes;

/**
 * Class to represent a great knife weapon item
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class GreatKnife extends WeaponItem{

    private int strengthRequirement;
    /**
     * Constructor.
     *
     * @param name                name of the item
     * @param displayChar         character to use for display when item is on the ground
     * @param damage              amount of damage this weapon does
     * @param verb                verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate             the probability/chance to hit the target.
     * @param strengthRequirement the strength needed to pick up the weapon
     */
    public GreatKnife(String name, char displayChar, int damage, String verb, int hitRate, int strengthRequirement, WeaponArt weaponArt) {
        super(name, displayChar, damage, verb, hitRate, weaponArt);
        this.strengthRequirement = strengthRequirement;
    }

    /**
     * List of allowable actions that can be performed on the item when it is on the ground.
     * Checks whether the weapon can be picked up depending on if the actor meets the strength requirement of the weapon
     * @param location the location of the ground on which the item lies
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions =  super.allowableActions(location);
        Actor actor = location.getActor();

        if (actor.getAttribute(ExtraActorAttributes.STRENGTH) < this.strengthRequirement && this.portable == true)
        {
            this.togglePortability();
        }
        else if (actor.getAttribute(ExtraActorAttributes.STRENGTH) >= this.strengthRequirement && this.portable == false)
        {
            this.togglePortability();
        }
        return actions;
    }
}
