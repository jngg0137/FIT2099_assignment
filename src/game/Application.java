package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.FurnaceGolem;
import game.actors.Player;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Puddle;
import game.grounds.Wall;
import game.consumables.FlaskOfHealing;
import game.consumables.FlaskOfRejuvenation;
import game.consumables.ShadowtreeFragment;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:Muhammad Hafiz Idlan Bin Hakimi
 */
public class Application {

    /**
     * Carries out the game
     * @param args arguments
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

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
        ShortSword shortSword = new ShortSword("Short Sword", '!', 100, "stabs", 75,10,null);
        GreatKnife greatKnife = new GreatKnife("Great Knife",'†', 75,"slashes",60,5,null);
        FlaskOfHealing flaskOfHealing = new FlaskOfHealing("Flask of Healing",'u',5);
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation("Flask of Rejuvenation",'o',3);
        ShadowtreeFragment shadowtreeFragment1 = new ShadowtreeFragment("Shadowtree Fragment",'e');
        ShadowtreeFragment shadowtreeFragment2 = new ShadowtreeFragment("Shadowtree Fragment",'e');
        ShadowtreeFragment shadowtreeFragment3 = new ShadowtreeFragment("Shadowtree Fragment",'e');
        ShadowtreeFragment shadowtreeFragment4 = new ShadowtreeFragment("Shadowtree Fragment",'e');
        ShadowtreeFragment shadowtreeFragment5 = new ShadowtreeFragment("Shadowtree Fragment",'e');
        GreatKnife greatKnifeWithLifesteal = new GreatKnife("Great Knife (LifeSteal)", '†', 25, "slashes", 80, 5, new LifeSteal());
        GreatKnife greatKnifeWithQuickstep = new GreatKnife("Great Knife (Quickstep)", '†', 25, "slashes", 80,5, new QuickStep());
        ShortSword shortSwordWithQuickstep = new ShortSword("Shortsword (Quickstep)", '!', 30, "stabs", 85, 10,new QuickStep());



        world.addPlayer(player, gameMap.at(7, 4));

        gameMap.at(42, 4).addActor(new FurnaceGolem());
        gameMap.at(0,9).addItem(shortSword);
        gameMap.at(1,9).addItem(greatKnife);
        gameMap.at(7,5).addItem(flaskOfHealing);
        gameMap.at(8,5).addItem(flaskOfRejuvenation);
        gameMap.at(0,0).addItem(shadowtreeFragment1);
        gameMap.at(4,5).addItem(shadowtreeFragment2);
        gameMap.at(14,8).addItem(shadowtreeFragment3);
        gameMap.at(13,3).addItem(shadowtreeFragment4);
        gameMap.at(15,6).addItem(shadowtreeFragment5);
        gameMap.at(7,8).addItem(shortSwordWithQuickstep);
        gameMap.at(8,8).addItem(greatKnifeWithLifesteal);
        gameMap.at(9,8).addItem(greatKnifeWithQuickstep);

        world.run();
        System.out.println(FancyMessage.YOU_DIED);
    }
}
