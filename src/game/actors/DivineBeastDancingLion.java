package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.divinepowers.DivinePower;
import game.divinepowers.WindDivinePower;
import game.items.GateItem;
import game.weapons.LionBite;
import game.items.RemembranceOfDancingLion;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;



/**
 * Class representing the Divine Beast Dancing Lion boss.
 *
 * @author Mohamad Kassem Alsabbagh
 * Modified by : Liew Zhe Xing 32619472
 */
public class DivineBeastDancingLion extends Actor {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private DivinePower currentDivinePower;
    private GameMap beluratMap;
    private Random random = new Random();
    private Actor target;
    private static final int DIVINE_POWER_CHANGE_CHANCE = 25;
    private Display display = new Display();

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

    /**
     * Selects and returns an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new DoNothingAction();  // Return a DoNothingAction if unconscious
        }

        if (target != null && (!target.isConscious() || !map.contains(target))) {
            target = null;
            behaviours.remove(1); // Remove follow behaviour
            behaviours.put(10, new WanderBehaviour()); // Add wander behaviour
        }


        if (target != null && (!target.isConscious() || !map.contains(target))) {
            target = null;
            behaviours.remove(1); // Remove follow behaviour
            behaviours.put(10, new WanderBehaviour()); // Add wander behaviour
        }

        Location here = map.locationOf(this);

        if (target == null) {
            // Look for actors in adjacent locations
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor potentialTarget = destination.getActor();
                    if (potentialTarget.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        target = potentialTarget;
                        behaviours.remove(10); // Remove wander behaviour
                        behaviours.put(1, new FollowBehaviour(target));
                        break;
                    }
                }
            }
        }

        if (target != null) {
            // Check if target is adjacent
            Location targetLocation = map.locationOf(target);
            boolean adjacent = false;
            String directionFromTargetToHere = "";

            for (Exit exit : targetLocation.getExits()) {
                if (exit.getDestination() == here) {
                    adjacent = true;
                    directionFromTargetToHere = exit.getName();
                    break;
                }
            }

            // 25% chance to change divine power
            int chance = random.nextInt(100);
            if (chance < DIVINE_POWER_CHANGE_CHANCE) {
                DivinePower newPower = currentDivinePower.determineNextDivinePower();
                if (!newPower.getName().equals(currentDivinePower.getName())) {
                    currentDivinePower = newPower;
                    display.println(this + " has changed its divine power to " + currentDivinePower.getName() + "!");
                }
            }

            // Execute special attack
            currentDivinePower.executeSpecialAttack(this, target, map);

            if (adjacent) {
                // Perform normal attack
                return new AttackAction(target, directionFromTargetToHere);
            }
        }

        // Execute behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a collection of the actions that the other actor can do to the Divine Beast.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // Actors that are HOSTILE_TO_ENEMY can attack the Divine Beast
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Handles the unconscious state of the Divine Beast Dancing Lion.
     *
     * @param map the GameMap containing the Divine Beast
     * @return a description of the unconscious state
     */
    @Override
    public String unconscious(GameMap map) {
        Location bossLocation = map.locationOf(this);

        // Create a gate back to Belurat
        GateItem gateToBelurat = new GateItem("Gate to Belurat", 'H', false);
        gateToBelurat.addGateAction(new MoveActorAction(beluratMap.at(2, 2), "to Belurat, Tower Settlement"));

        // Place the gate at the boss's location
        bossLocation.addItem(gateToBelurat);

        // Drop the Remembrance of the Dancing Lion at the boss's location
        bossLocation.addItem(new RemembranceOfDancingLion());

        display.println(this + " has been defeated! A gate back to Belurat has appeared.");
        display.println(this + " dropped the Remembrance of the Dancing Lion.");

        return super.unconscious(map);
    }



}
