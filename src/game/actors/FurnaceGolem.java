package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.FurnaceGolemLeg;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing the Furnace Golem enemy
 * The golem is able to attack any enemy that is hostile towards it as well as follow them.
 * @author Adrian Kristanto
 * Modified by : Muhammad Hafiz Idlan Bin Hakimi
 *               Ng Jiunn Chieh 33590850
 */
public class FurnaceGolem extends Actor {
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private final int wanderBehaviourPriority = 999;
    private final int attackBehaviourPriority = 1;
    private final int followBehaviourPriority = 2;

    /**
     * Constructor for the FurnaceGolem class
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.behaviours.put(wanderBehaviourPriority, new WanderBehaviour());
        this.setIntrinsicWeapon(new FurnaceGolemLeg());
        this.addCapability(Ability.IMMUNE_TO_BURNING);
    }

    /**
     * Selects an action for the Furnace Golem to perform on its turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action that the Furnace Golem can perform
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
     * Adds behaviours to the Furnace Golem and creates actions that an actor may perform to the Furnace Golem itself
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            this.behaviours.put(attackBehaviourPriority,new AttackBehaviour(otherActor,this.getIntrinsicWeapon()));
            this.behaviours.put(followBehaviourPriority,new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
