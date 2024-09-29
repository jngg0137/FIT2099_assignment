package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.FurnaceGolem;
import game.actors.Player;
import game.actors.Spirit;
import game.grounds.*;
import game.consumables.FlaskOfHealing;
import game.consumables.FlaskOfRejuvenation;
import game.consumables.ShadowtreeFragment;
import game.items.GateItem;
import game.items.Graveyard;
import game.items.Spawner;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:Muhammad Hafiz Idlan Bin Hakimi
 *
 */
public class Application {

    /**
     * Carries out the game
     * @param args arguments
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        // Ground factories for the maps
        FancyGroundFactory groundFactory = new FancyGroundFactory(
                new Dirt(), new Wall(), new Floor(), new Puddle()
        );

        FancyGroundFactory beluratTowerGroundFactory = new FancyGroundFactory(
                new Dirt(), new Wall(), new Floor(), new Puddle()
        );

        // Include Puddle in beluratSewersGroundFactory to handle '~'
        FancyGroundFactory beluratSewersGroundFactory = new FancyGroundFactory(
                new Dirt(), new Wall(), new Floor(), new PoisonSwamp(), new Puddle()
        );

        // Gravesite Plain map
        List<String> map = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gameMap = new GameMap("Gravesite Plain", groundFactory, map);
        world.addGameMap(gameMap);

        // Belurat Tower Settlement map
        List<String> beluratTowerMap = Arrays.asList(
                "###########........................##########",
                "#____#____#......................._____#____#",
                "#____#_.._#.#...~~~.......~~~....#____#____##",
                "###_~~____###...~~~..~~~..~~~...####______###",
                "###...____###..~~~~..~~~~..~~~...######_____#",
                "##~~###..####..~~~...~~~.....~~~..####..#####",
                "##__.....####..~~~.~~~~~..~~~....#####____###",
                "###..##..##.#..~~..~~~~~..~~~~....####~..####",
                "#....__..__.#..~~..~~~~~~..~~....__~~~~######",
                "###########....................##############"
        );
        GameMap beluratTowerGameMap = new GameMap("Belurat, Tower Settlement",
                beluratTowerGroundFactory, beluratTowerMap);
        world.addGameMap(beluratTowerGameMap);

        // Belurat Sewers map
        List<String> beluratSewersMap = Arrays.asList(
                "##++++++#####++++++++~~~~~++++",
                "##+++++++###+++++++++~~~~~++++",
                "##++++++++++++++++++~~~~~~~++~",
                "###+++++++++++++++.~~~~~~~~.~~",
                "~~~~~.+++++~~~++++~~~~~~~~~..~",
                "~~~~~~~~~~~~~~~++++~~~~+++~...",
                "~~~~+~~~~~~~~~~+++++~~~~~~~###",
                "+~~~~++####~~~~~++++##.~++~###",
                "++~~+++#####~~~~~++###++~~~###",
                "+~~++++######~~~~++###++~~~###"
        );
        GameMap beluratSewersGameMap = new GameMap("Belurat Sewers",
                beluratSewersGroundFactory, beluratSewersMap);
        world.addGameMap(beluratSewersGameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Tarnished", '@', 150);

        // Weapons without weapon arts
        ShortSword shortSword = new ShortSword("Short Sword", '!',
                100, "stabs", 75, 10);
        GreatKnife greatKnife = new GreatKnife("Great Knife", '†',
                75, "slashes", 60, 5);

        // Consumables
        FlaskOfHealing flaskOfHealing = new FlaskOfHealing("Flask of Healing", 'u', 5);
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation("Flask of Rejuvenation", 'o', 3);
        ShadowtreeFragment shadowtreeFragment1 = new ShadowtreeFragment("Shadowtree Fragment", 'e');
        ShadowtreeFragment shadowtreeFragment2 = new ShadowtreeFragment("Shadowtree Fragment", 'e');
        ShadowtreeFragment shadowtreeFragment3 = new ShadowtreeFragment("Shadowtree Fragment", 'e');
        ShadowtreeFragment shadowtreeFragment4 = new ShadowtreeFragment("Shadowtree Fragment", 'e');
        ShadowtreeFragment shadowtreeFragment5 = new ShadowtreeFragment("Shadowtree Fragment", 'e');

        // Add player to the game map
        world.addPlayer(player, gameMap.at(7, 4));

        // Add actors and items to the Gravesite Plain map
        gameMap.at(42, 4).addActor(new FurnaceGolem());
        gameMap.at(0, 9).addItem(shortSword);
        gameMap.at(1, 9).addItem(greatKnife);
        gameMap.at(7, 5).addItem(flaskOfHealing);
        gameMap.at(8, 5).addItem(flaskOfRejuvenation);
        gameMap.at(0, 0).addItem(shadowtreeFragment1);
        gameMap.at(4, 5).addItem(shadowtreeFragment2);
        gameMap.at(14, 8).addItem(shadowtreeFragment3);
        gameMap.at(13, 3).addItem(shadowtreeFragment4);
        gameMap.at(15, 6).addItem(shadowtreeFragment5);

        // Single Gate with multiple destinations
        GateItem gateToBelurat = new GateItem("Gate", 'H', false);

        // Create MoveActorActions for each destination
        MoveActorAction moveToBeluratTower = new MoveActorAction(beluratTowerGameMap.at(2, 2),
                "to Belurat, Tower Settlement");
        MoveActorAction moveToBeluratSewers = new MoveActorAction(beluratSewersGameMap.at(4, 4),
                "to Belurat Sewers");

        // Add actions to the gate
        gateToBelurat.addGateAction(moveToBeluratTower);
        gateToBelurat.addGateAction(moveToBeluratSewers);

        // Add gate to the Gravesite Plain map
        gameMap.at(43, 3).addItem(gateToBelurat);

        // Gate back to Gravesite Plain
        GateItem gateToGravesite = new GateItem("Gate", 'H', false);
        MoveActorAction moveToGravesite = new MoveActorAction(gameMap.at(7, 4), "back to the Gravesite Plains");
        gateToGravesite.addGateAction(moveToGravesite);

        // Add gate to the Belurat maps
        beluratTowerGameMap.at(3, 3).addItem(gateToGravesite);
        beluratSewersGameMap.at(0, 4).addItem(gateToGravesite);

        // Add 5 graveyards to Belurat Tower
        Spawner graveyardSpirit = new Spawner(new Graveyard(),new Spirit(),20);
        beluratTowerGameMap.at(20,1).addItem(graveyardSpirit);
        beluratTowerGameMap.at(25,1).addItem(graveyardSpirit);
        beluratTowerGameMap.at(20,2).addItem(graveyardSpirit);
        beluratTowerGameMap.at(25,2).addItem(graveyardSpirit);
        beluratTowerGameMap.at(20,9).addItem(graveyardSpirit);

        // Start the game
        world.run();
        System.out.println(FancyMessage.YOU_DIED);
    }
}
