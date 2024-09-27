package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.positions.Location;
import java.util.Random;
import game.actors.Player;
import game.weapons.WeaponItem;

public class LifeSteal implements WeaponArt {
    private static final int MANA_COST = 10;
    private static final int HEAL_AMOUNT = 20;

    @Override
    public String use(Actor attacker, Actor target, WeaponItem weapon) {
        if (attacker instanceof Player) {
            Player player = (Player) attacker;
            if (player.getMana() >= MANA_COST) {
                player.reduceMana(MANA_COST);
                player.heal(HEAL_AMOUNT);
                target.hurt(weapon.getDamage());
                return "Lifesteal activated! Player healed for " + HEAL_AMOUNT + " HP.";
            }
        }
        return "Not enough mana for Lifesteal.";
    }


}
