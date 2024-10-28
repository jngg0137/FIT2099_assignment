package game.actors;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public enum Ability {
    /**
     * Indicates whether an actor can enter a floor ground
     */
    ENTER_FLOOR,
    /**
     * Indicates whether an actor or a ground can be burnt
     */
    IMMUNE_TO_BURNING,
    /**
     * Indicates whether an actor can be poisoned
     */
    IMMUNE_TO_POISON,
    /**
     * Indicates whether an item can be loaded into an item that uses bolts
     */
    LOADABLE_BOLTS,
}