package game.actors.state;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.ExtraActorAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the state of the target actor's attributes taken at a certain time
 */
public class ActorAttributesState extends State {

    Map<Enum<?>, Integer> savedAttributes = new HashMap<>();

    /**
     * Constructor. Takes the attributes that the actor has with corresponding values and records it.
     * @param target the actor whose state is recorded
     */
    public ActorAttributesState(Actor target) {
        super(target);

        for (BaseActorAttributes baseActorAttributes : BaseActorAttributes.values()){

            if (target.hasAttribute(baseActorAttributes)){
                savedAttributes.put(baseActorAttributes, target.getAttribute(baseActorAttributes));
            }

        }

        for (ExtraActorAttributes extraActorAttributes : ExtraActorAttributes.values()){

            if (target.hasAttribute(extraActorAttributes)){
                savedAttributes.put(extraActorAttributes, target.getAttribute(extraActorAttributes));
            }

        }

    }

    /**
     * Refreshes/Reverts the target actor's current state to the recorded one
     * @return String indicating that the target's state has been refreshed/reverted
     */
    @Override
    public String useState() {
        Actor target = getTarget();
        for (Map.Entry<Enum<?>, Integer> entry : savedAttributes.entrySet()) {
            target.modifyAttribute(entry.getKey(), ActorAttributeOperations.UPDATE, entry.getValue());
        }
        return target + " attributes has been reverted to last recorded state";
    }
}
