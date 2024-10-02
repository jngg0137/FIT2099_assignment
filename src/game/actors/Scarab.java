package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.behaviours.WanderBehaviour;
import game.consumables.Consumable;
import game.consumables.CrimsonTear;
import game.effects.AttributeBuff;
import game.effects.Explosion;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing the Scarab NPC
 * The Scarab can be consumed as well as attacked
 * The Scarab wanders around the map it is in
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class Scarab extends Actor implements Consumable {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private final int wanderBehaviourPriority = 999;

    /**
     * The constructor of the Scarab class.
     */
    public Scarab() {
        super("Scarab", 'b', 25);
        this.behaviours.put(wanderBehaviourPriority, new WanderBehaviour());
    }

    /**
     * Selects an action for the Scarab to perform on its turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action that the Scarab can perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Creates actions that an actor may perform to the Scarab
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * An actor is able to consume the Scarab, killing it in the process.
     * The actor will be granted buff effects to certain attributes that last for a certain duration.
     * @param actor the actor that is consuming
     * @param gameMap the map in which the consuming is performed
     * @return a description of what occurs when the Scarab is consumed
     */
    @Override
    public String consume(Actor actor, GameMap gameMap) {

        this.unconscious(gameMap);
        actor.addStatusEffect(new AttributeBuff(actor,BaseActorAttributes.HEALTH, 10,30));
        actor.addStatusEffect(new AttributeBuff(actor,BaseActorAttributes.MANA, 10, 50));
        return String.format("%s maximum health and mana increases by %d and %d respectively for %d turns!",actor, 30,50,10);
    }

    /**
     * Triggers when the Scarab is dead upon means other than consuming (e.g. An Actor killing the Scarab with its weapon).
     * When dead, an explosion occurs as well as an item being dropped at the Scarab's death location.
     * @param actor the perpetrator
     * @param map   where the actor fell unconscious
     * @return a description of what occurs upon the Scarab's death
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location locationOfScarab = map.locationOf(this);
        super.unconscious(actor,map);

        Explosion explosion = new Explosion(25,locationOfScarab);
        explosion.explode();

        CrimsonTear crimsonTear = new CrimsonTear();
        locationOfScarab.addItem(crimsonTear);

        return String.format("%s death resulted in an explosion! The %s left a %s on the ground.",this,this,crimsonTear);

    }
}
