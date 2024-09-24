package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing the attack behaviour of an actor NPC
 * @author Muhammad Hafiz Idlan
 */
public class AttackBehaviour extends Action implements Behaviour {

    private final Actor target;
    private final Weapon weaponUsed;

    /**
     * Constructor
     * @param target the target of the attack
     * @param weaponUsed the weapon used for the attack
     */
    public AttackBehaviour(Actor target,Weapon weaponUsed)
    {
        this.target = target;
        this.weaponUsed = weaponUsed;
    }

    /**
     * Performs the action to attack
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = weaponUsed.attack(actor,target,map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor,map);
        }
        return result;
    }

    /**
     * Does not describe the action on the menu as the actor is an NPC
     * @param actor The actor performing the action.
     * @return an empty string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * The attack logic of the actor, the target must be close enough to the actor to perform the attack
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the attack behaviour or null if the target is not close to the actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int xDifference = there.x() - here.x();
        int yDifference = there.y() - here.y();

        if (Math.abs(xDifference) <= 1 && Math.abs(yDifference) <= 1)
        {
            return this;
        }
        return null;
    }

}
