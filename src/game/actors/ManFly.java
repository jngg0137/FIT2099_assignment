package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the man-fly NPC
 * Once the Tarnished is within its surroundings, the man-fly will
 * follow the Tarnished around until either of them dies.
 *
 * @author Ng Jiunn Chieh 33590850
 */
public class ManFly extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public ManFly(){
        super("Man-fly",'%',50);
        this.behaviours.put(999, new WanderBehaviour());
//        this.setIntrinsicWeapon(new BareFist()); to replace with sting attack
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
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
     * Adds behaviours to the man-fly and creates actions that an actor may perform to the man-fly
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            this.behaviours.put(1,new AttackBehaviour(otherActor,this.getIntrinsicWeapon()));
            this.behaviours.put(2,new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
