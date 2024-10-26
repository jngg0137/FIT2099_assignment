package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.ReloadAction;
import game.actors.Ability;
import game.items.ReloadableItem;
import game.weaponarts.WeaponArt;

import java.util.Random;


public class Crossbow extends RangedWeaponItem implements ReloadableItem {
    private Item bolt = null;

    /**
     * Constructor
     *
     * @param name          name of the item
     * @param displayChar   character to use for display when item is on the ground
     * @param damage        amount of damage this weapon does
     * @param verb          verb to use for this weapon
     * @param hitRate       the probability/chance to hit the target
     * @param weaponArt     Weapon art to give this weapon
     */
    public Crossbow(String name, char displayChar, int damage, String verb, int hitRate, WeaponArt weaponArt, int range){
        super(name,displayChar,damage,verb,hitRate,weaponArt,range);
    }

    /**
     * Load ammunition into the item
     *
     * @param actor         the actor reloading the item
     * @param ammunition    ammunition to load into the item
     * @return              a description of what occurs when reloading
     */
    @Override
    public String load(Actor actor, Item ammunition) {
        String ret = "";
        if (bolt != null){
            actor.addItemToInventory(bolt);
            ret += actor + " unloads " + bolt + " from " + this + "\n";
        }
        bolt = ammunition;
        actor.removeItemFromInventory(ammunition);
        ret += actor + " loads " + this + " with " + ammunition;
        return ret;
    }

    /**
     * Performs an attack to a target, has a chance to land a hit successfully
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a description of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        String ret = "";
        Random rand = new Random();
        if (!(rand.nextInt(100) < super.getHitRate())) {
            bolt = null;
            return attacker + " misses " + target + ".";
        }

        // Check if weapon art is present
        if (super.getWeaponArt() != null) {
            // If player has enough mana, use weapon art
            if (attacker.getAttribute(BaseActorAttributes.MANA) >= super.getWeaponArt().getManaCost()) {
                ret += super.getWeaponArt().use(attacker, target, map);
            }
            else
            {
                ret += "Not enough mana available to trigger weapon art!";
            }
        }

        ret += String.format("\n%s %s at %s\n", attacker, super.getVerb(), target);
        // Carry out on hit effects of the bolt
        ret += bolt.onRangedHit(attacker,target,map);
        bolt = null;
        return ret;
    }

    /**
     * List of allowable actions that the item can perform to the current actor
     * Example #1: a healing item can have a special skill that can increase the current actor's hitpoints.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);

        for (Item item : owner.getItemInventory()) {
            if (item.hasCapability(Ability.LOADABLE_BOLTS)) {
                actions.add(new ReloadAction(this, item));
            }
        }

        return actions;
    }

    /**
     * List of allowable actions that the actor can perform with the ranged item
     *
     * @param owner     actor that has this item in inventory
     * @param location  location of actor that has this item in inventory
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableRangedActions(Actor owner, Location location){
        ActionList actions = new ActionList();

        if (bolt != null) {
            // Generate attack actions if the crossbow is loaded
            for (Location suroundingLocation : location.getSurroundingLocations(super.getRange() * 2)) {
                if (suroundingLocation.containsAnActor()) {
                    actions.add(new AttackAction(suroundingLocation.getActor(), suroundingLocation.toString(), this));
                }
            }
        }
        return actions;
    }
}
