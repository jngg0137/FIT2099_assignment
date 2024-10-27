package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.Ability;
import game.actors.Scarab;
import game.consumables.Consumable;

import java.util.List;
import java.util.Random;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Muhammad Hafiz Idlan Bin Hakimi (33589690)
 *
 */
public class Puddle extends Ground implements Consumable {
    private static final int MANA_POINTS = 5;
    /**
     * Constructor
     */
    public Puddle() {
        super('~', "Puddle");
        this.addCapability(Ability.IMMUNE_TO_BURNING);
        this.addCapability(GroundCapability.WATER);
    }

    /**
     * Restores the actor's mana by a certain amount upon consuming the puddle
     * Also has a chance to spawn a scarab
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when consuming the puddle
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        String message = String.format("%s mana restored by %d points.", actor, MANA_POINTS);
        Location puddleLocation = gameMap.locationOf(actor);
        List<Exit> puddleSurroundings = puddleLocation.getExits();
        Random rand = new Random();
        int scarabSpawnChance = 10;

        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_POINTS);

        if (rand.nextInt(100) < scarabSpawnChance) {
            Exit randomExit = puddleSurroundings.get(rand.nextInt(puddleSurroundings.size()));
            Location scarabSpawnLocation = randomExit.getDestination();
            scarabSpawnLocation.addActor(new Scarab());
            message += String.format("\n%s spawned from consuming %s", scarabSpawnLocation.getActor(), this);
        }

        return message;
    }

    /**
     * List of allowable actions that can be performed on the puddle (e.g. An actor being able to consume it)
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor,location,direction);
        // Ensures that the puddle can only be consumed if the actor is standing on it
        if (location.containsAnActor()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}