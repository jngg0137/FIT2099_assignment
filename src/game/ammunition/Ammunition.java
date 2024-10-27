package game.ammunition;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents items that can be used as ammunition by other items
 *
 * @author Ng Jiunn Chieh 33590850
 */
public abstract class Ammunition extends Item {

    /**
     * Constructor
     *
     * @param name  the name of this Item
     * @param displayChar   the character to use to represent this item if it is on the ground
     * @param portable  true if and only if the Item can be picked up
     */
    public Ammunition(String name, char displayChar, boolean portable){
        super(name,displayChar,portable);
    }

    /**
     * Determines and returns the locations the projectile will traverse whilst trying to reach the location
     * it targeted
     *
     * @param originalLocation  location the projectile was fired from
     * @param intendedLocation  location the projectile was targeting
     * @return          location the projectile should land on
     */
    public List<Location> calculateTrajectory(Location originalLocation, Location intendedLocation){
        List<Location> ret = new ArrayList<>();
        GameMap map = originalLocation.map();
        NumberRange heights = map.getYRange();
        NumberRange widths = map.getXRange();
        int projectileLocationX = intendedLocation.x();
        int projectileLocationY = intendedLocation.y();
        int xDifference = originalLocation.x() - intendedLocation.x();
        int yDifference = originalLocation.y() - intendedLocation.y();

        if (xDifference == 0){
            // north trajectory
            if (yDifference > 0){
                while (projectileLocationY > heights.min()){
                    projectileLocationY -= 1;
                    ret.add(map.at(projectileLocationX,projectileLocationY));
                }
                // south trajectory
            } else if (yDifference < 0) {
                while (projectileLocationY < heights.max()){
                    projectileLocationY += 1;
                    ret.add(map.at(projectileLocationX,projectileLocationY));
                }
            }
        } else if (yDifference == 0) {
            // west trajectory
            if (xDifference < 0){
                while (projectileLocationX < widths.max()){
                    projectileLocationX += 1;
                    ret.add(map.at(projectileLocationX,projectileLocationY));
                }
                // east trajectory
            } else if (xDifference > 0) {
                while (projectileLocationX > widths.min()){
                    projectileLocationX -= 1;
                    ret.add(map.at(projectileLocationX,projectileLocationY));
                }
            }
        } else {
            // use y = mx + c to determine trajectory if it's not a horizontal or vertical line
            float m = (float) yDifference / xDifference;
            float c = projectileLocationY - (m * projectileLocationX);
            if (yDifference > 0){
                // north-west trajectory
                if (xDifference > 0) {
                    while (projectileLocationX > widths.min()) {
                        projectileLocationX -= 1;
                        projectileLocationY = (int) Math.floor((m * projectileLocationX) + c);
                        if (projectileLocationY < heights.min()) {
                            projectileLocationY = heights.min();
                            ret.add(map.at(projectileLocationX,projectileLocationY));
                            return ret;
                        }
                        ret.add(map.at(projectileLocationX,projectileLocationY));
                    }
                    // north-east trajectory
                } else if (xDifference < 0) {
                    while (projectileLocationX < widths.max()){
                        projectileLocationX += 1;
                        projectileLocationY = (int) Math.floor((m * projectileLocationX) + c);
                        if (projectileLocationY < heights.min()) {
                            projectileLocationY = heights.min();
                            ret.add(map.at(projectileLocationX,projectileLocationY));
                            return ret;
                        }
                        ret.add(map.at(projectileLocationX,projectileLocationY));
                    }
                }

//                if (projectileLocationY > intendedLocation.y()){
//                    projectileLocationY = intendedLocation.y() - 1;
//                }
            } else if (yDifference < 0) {
                // south-west trajectory
                if (xDifference > 0) {
                    while (projectileLocationX > widths.min()) {
                        projectileLocationX -= 1;
                        projectileLocationY = (int) Math.ceil((m * projectileLocationX) + c);
                        if (projectileLocationY > heights.max()) {
                            projectileLocationY = heights.max();
                            ret.add(map.at(projectileLocationX,projectileLocationY));
                            return ret;
                        }
                        ret.add(map.at(projectileLocationX,projectileLocationY));
                    }
                    // south-east trajectory
                } else if (xDifference < 0) {
                    while (projectileLocationX < widths.max()){
                        projectileLocationX += 1;
                        projectileLocationY = (int) Math.ceil((m * projectileLocationX) + c);
                        if (projectileLocationY > heights.max()) {
                            projectileLocationY = heights.max();
                            ret.add(map.at(projectileLocationX,projectileLocationY));
                            return ret;
                        }
                        ret.add(map.at(projectileLocationX,projectileLocationY));
                    }
                }

//                if (projectileLocationY < intendedLocation.y()){
//                    projectileLocationY = intendedLocation.y() + 1;
//                }
            }
        }

//        if (projectileLocationY < heights.min()){
//            projectileLocationY = heights.min();
//        } else if (projectileLocationY > heights.max()) {
//            projectileLocationY = heights.max();
//        }

        return ret;
    }

    /**
     * Define what happens to the user, target or both when the target is hit by this item via ranged attack
     *
     * @param attacker  actor using this item
     * @param target    actor being attacked
     * @param map       map this item is used
     * @param hitTarget	was the target hit
     * @return          a description of what occurs when using this item
     */
    @Override
    public abstract String rangedEffect(Actor attacker, Actor target, GameMap map, boolean hitTarget);
}
