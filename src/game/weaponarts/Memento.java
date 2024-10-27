package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.StateManager;

import java.util.Random;

/**
 * Class representing the Memento weapon art
 * @author Muhammad Hafiz Idlan Bin Hakimi (33589690)
 */
public class Memento implements WeaponArt{

    private StateManager stateManager;
    private static final int HEALTH_COST = 5;

    /**
     * Constructor for the class.
     * Creates a StateManager to organize the states being recorded and used for the different actors
     */
    public Memento(){
        this.stateManager = new StateManager();
    }

    /**
     * The weapon art can do 2 things, snapshotting an actor's state or refreshing an actor's state to the last recorded
     * state that was snapshotted. After a snapshot or refresh happens, the user of the weapon art will lose 5 health
     * points
     * Snapshotting :
     * The weapon art has a 50% chance to create a "snapshot" of the user actor's state (e.g. Attributes, Location, etc)
     * at time of use and record it in the StateManager. What states are recorded depend on the actor, some may save
     * just attributes, some may save status effects, or maybe a combination.
     * Refreshing :
     * The other 50% chance "refreshes" the actor's state by using the last recorded state that was snapshot from
     * the StateManager if there is one
     * @param attacker the actor using the weapon art
     * @param target the target that is being attacked (not
     * @param map the map where the weapon art is used
     * @return a String indicating whether Memento activated or not
     */
    @Override
    public String use(Actor attacker, Actor target, GameMap map) {

        if (attacker.getAttribute(BaseActorAttributes.HEALTH) <= HEALTH_COST){
            return attacker + " health is too low for Memento to activate!";
        }

        String message = "";
        Random rand = new Random();
        int snapshotChance = 50;

        if (rand.nextInt(100) < snapshotChance){
            message = stateManager.createStateBasedOfTargetForCopier(attacker,attacker);
        }
        else {
            message = stateManager.useLastRecordedStateFromCopier(attacker);
        }

        attacker.hurt(HEALTH_COST);
        return "Memento activated! " + message;
    }

    /**
     * the method getManaCost allows users to get the information of the mana they have left.
     * @return MANA_COST
     */
    @Override
    public int getManaCost() {
        return 0;
    }
}
