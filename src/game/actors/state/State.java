package game.actors.state;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing a state of a target Actor taken at a certain time
 */
public abstract class State {

    private Actor target;

    /**
     * Constructor
     * @param target the actor whose state is recorded
     */
    public State(Actor target){
        this.target = target;
    }

    /**
     * Refreshes/Reverts the target actor's current state to the recorded one
     * @return String indicating that the target's state has been refreshed/reverted
     */
    public abstract String useState();

    /**
     * Obtains the target actor whose state is recorded
     * @return the target actor
     */
    public Actor getTarget() {
        return target;
    }
}
