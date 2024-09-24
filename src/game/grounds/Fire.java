package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ability;
import game.effects.Burning;

/**
 * Represents a ground that is caught on fire
 * @author Muhammad Hafiz Idlan Bin Hakimi
 */
public class Fire extends Ground {

    private int duration;
    private final Ground previousGround;

    /**
     * Constructor
     * @param duration how long the fire lingers
     * @param previousGround the ground that will be caught on fire
     */
    public Fire(int duration,Ground previousGround) {
        super('w', "Fire");
        this.duration =  duration;
        this.previousGround = previousGround;

    }

    /**
     * Adds a burning effect to the actor in the fire unless immune. Once fire wears off, ground is set to its original
     * state before
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){

        Actor actor = location.getActor();
        if (location.containsAnActor() && !actor.hasCapability(Ability.IMMUNE_TO_BURNING) && duration != 0)
        {
            Burning burning = new Burning(5,1,this);
            actor.addStatusEffect(burning);

        }

        if (duration == 0)
        {
            //if the previous ground happens to be Fire, it will be set to fire and continue to tick down the duration,
            // thus the duration appears to stack
            location.setGround(previousGround);
        }

        duration -= 1;
    }

    /**
     * Determines if the ground is passable by an actor, based on the properties of the ground that got set on fire
     * @param actor the Actor to check
     * @return true or false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return previousGround.canActorEnter(actor);
    }


}
