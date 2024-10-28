package game.actors;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * Indicates whether an actor is hostile to the enemy
     */
    HOSTILE_TO_ENEMY,
    /**
     * Indicates whether an actor can be attacked
     */
    NON_ATTACKABLE
}
