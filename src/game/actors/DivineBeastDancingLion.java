package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
import game.divinepowers.DivinePower;
import game.divinepowers.WindDivinePower;
import game.weapons.LionBite;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Class representing the Divine Beast Dancing Lion boss.
 */
public class DivineBeastDancingLion extends Actor {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private DivinePower currentDivinePower;
    private GameMap beluratMap;
    private Random random = new Random();

    /**
     * Constructor for the DivineBeastDancingLion.
     *
     * @param beluratMap The GameMap representing Belurat, Tower Settlement
     */
    public DivineBeastDancingLion(GameMap beluratMap) {
        super("Divine Beast Dancing Lion", 'S', 10000);
        this.currentDivinePower = new WindDivinePower();
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new WanderBehaviour());
        this.beluratMap = beluratMap;
        this.setIntrinsicWeapon(new LionBite());
    }
}
