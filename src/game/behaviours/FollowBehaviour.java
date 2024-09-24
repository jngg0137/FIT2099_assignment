package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the follow behaviour of an actor NPC
 * @author Muhammad Hafiz Idlan
 */
public class FollowBehaviour implements Behaviour {

    private final Actor target;

    /**
     * Constructor
     * @param target the target to be followed
     */
    public FollowBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * The follow logic of the actor, it will only follow once the actor is close to the target and tries to escape.
     * The actor will keep following the target until either of them dies
     * Actor chooses the optimal location to walk to
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a move action for the actor or null if there are no valid locations to move or if the map does not
     * contain either the target or actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        List<Exit> validExits = new ArrayList<>();

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                validExits.add(exit);
            }
        }

        if (validExits.isEmpty()) {
            return null;
        }

        int lowestDistance = distance(validExits.getFirst().getDestination(), there);
        Location bestDestination = validExits.getFirst().getDestination();
        Exit bestExit = validExits.getFirst();

        for (Exit validExit : validExits)
        {
            Location destination = validExit.getDestination();
            int distanceDestination = distance(destination,there);
            if (distanceDestination < lowestDistance)
            {
               lowestDistance = distanceDestination;
               bestDestination = destination;
               bestExit = validExit;
            }
        }

        return new MoveActorAction(bestDestination, bestExit.getName() + ". " + actor + " is following " + there.getActor());

    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
