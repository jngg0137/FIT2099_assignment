package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a StateManager. Helps in managing the various State(s) recorded and used by different actors
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class StateManager {

    private Map<Actor, ArrayList<ArrayList<State>>> recordedStates;

    /**
     * Constructor for StateManager. Creates HashMap to store Actor and State key value pairs
     */
    public StateManager(){
        this.recordedStates = new HashMap<>();
    }

    /**
     * Creates a State of the target for the copier to use against the target in the future. Depending on the
     * capabilities that the target has (e.g. a target may only be able to have their attributes copied, or a location,
     * or maybe both) some aspects of their State will be recorded. The target and copier can be the same, so that the
     * actor can create and use their state.
     * @param target the actor to copy state of
     * @param copier the actor that is doing the copying for their use against the target
     * @return a String indicating whether the state has been copied or not
     */
    public String createStateBasedOfTargetForCopier(Actor target, Actor copier){

        if (target.findCapabilitiesByType(CopyableState.class).isEmpty()){
            return target + " does not have the capability for their states to be copied";
        }

        if (!this.recordedStates.containsKey(copier)){
            this.recordedStates.put(copier, new ArrayList<>());
        }

        ArrayList<State> states = new ArrayList<>();
        if (target.hasCapability(CopyableState.ATTRIBUTES))
        {
            ActorAttributesState actorAttributesState = new ActorAttributesState(target);
            states.add(actorAttributesState);

        }

        this.recordedStates.get(copier).add(states);

        return target + " state is copied!";

    }

    /**
     * If available, gets the last recorded state by the copier for them to refresh/revert the target's state that was
     * created.
     * @param copier the actor that copies the created state for their use
     * @return a String indicating whether the states of the target had been refreshed/reverted
     */
     public String useLastRecordedStateFromCopier(Actor copier){
        if (this.recordedStates.isEmpty() || this.recordedStates.get(copier).isEmpty()) {
            return copier + " has no recorded states to use";
        }
        ArrayList<State> stateToUse = this.recordedStates.get(copier).getLast();
        String message = "";

        for (State state : stateToUse){
            message += state.useState();
        }

        return message;
    }



}
