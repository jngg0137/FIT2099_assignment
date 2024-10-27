package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.weapons.BareFist;
import game.effects.AttributeRestoring;

import java.util.List;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Muhammad Hafiz Idlan Bin Hakimi
 *              Liew Zhe Xing 32619472
 *
 */

public class Player extends Actor {

    private Display display = new Display();
    int maxMana;
    int maxHealth;
    int currentMana;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.maxHealth = hitPoints;  // Initialize max health
        this.maxMana = 100;          // Initialize max mana
        this.currentMana = maxMana;  // Set current mana to full
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(100));
        this.addAttribute(ExtraActorAttributes.STRENGTH, new BaseActorAttribute(5));
        this.addCapability(Ability.ENTER_FLOOR);
        this.setIntrinsicWeapon(new BareFist());
    }

    /**
     * Selects an action for the Player to perform on their turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action that the Player can perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Display player attributes
        this.display.println("Name: " + this.name + "\nHealth: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + "\nMana: " + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + "\nStrength: " + this.getAttribute(ExtraActorAttributes.STRENGTH));

        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Increase the player's maximum health.
     * @param amount The amount to increase the maximum health by.
     */
    public void increaseMaxHealth(int amount) {
        this.maxHealth += amount;  // Increase the player's max health
        this.heal(amount);         // Also heal the player by the added amount
    }

    /**
     * Increase the player's maximum mana and refill to new maximum.
     * @param amount The amount to increase the maximum mana by.
     */
    public void increaseMaxMana(int amount) {
        this.maxMana += amount;  // Increase the player's max mana
        this.refillMana(amount); // Add the same amount to current mana as a refill
    }

    public void refillMana(int amount) {
        // Refill mana, ensuring it doesn’t exceed the new maxMana
        this.currentMana = Math.min(this.currentMana + amount, this.maxMana);
    }

    /**
     * Adds a healing-over-time effect to the player
     */
    public void addHealingOverTimeEffect() {
        this.addStatusEffect(new AttributeRestoring(BaseActorAttributes.HEALTH, 3, 10)); // Heals 10 HP over 3 turns
    }

    /**
     * Checks if the player has a specific item in their inventory.
     * @param item The item to check for.
     * @return True if the item is in the inventory, false otherwise.
     */
    public boolean hasItem(Item item) {
        return this.getItemInventory().contains(item);
    }

    /**
     * Gets the player's current inventory.
     * @return A list of items in the inventory.
     */
    public List<Item> getInventory() {
        return this.getItemInventory(); // Uses the inherited inventory list
    }
}
