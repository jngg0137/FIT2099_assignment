package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Spawnable;

import java.util.List;
import java.util.Random;

public class Spawner extends Item {
    final Spawnable actorToSpawn;
    final int spawnChance;

    /***
     * Constructor.
     * @param item item that can spawn enemies from it
     * @param actorToSpawn actor that will be spawned
     * @param spawnChance chance for the actor to spawn per game turn
     */
    public Spawner(Item item, Spawnable actorToSpawn, int spawnChance) {
        super(item.toString(),item.getDisplayChar(),false);
        this.actorToSpawn = actorToSpawn;
        this.spawnChance = spawnChance;
    }

    /**
     * Spawn the desired enemy in a nearby tile
     *
     * @param location The location of the graveyard
     */
    @Override
    public void tick(Location location){
        Random random = new Random();

        if (random.nextInt(100) < spawnChance) {
            //if location contains an actor, scan for its exits that don't
            if (location.containsAnActor()){
                int i = 0;
                boolean continueLooking = true;
                List<Exit> exits = location.getExits();

                while (continueLooking){
                    Location exitLocation = exits.get(i).getDestination();
                    if(exitLocation.containsAnActor()){
                        i += 1;
                    } else {
                        exitLocation.addActor(actorToSpawn.newActor());
                        continueLooking = false;
                    }
                }
            } else {
                location.addActor(actorToSpawn.newActor());
            }
        }
    }
}
