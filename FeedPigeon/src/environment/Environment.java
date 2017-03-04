package environment;

import pigeons.Pigeon;
import pigeons.PigeonSet;
import java.util.Date;
import java.util.Random;

/**
 * Created by dhawo on 27/02/2017.
 */

public class Environment {
    private Map map;
    private PigeonSet pigeonSet = new PigeonSet();
    private static int NB_PIGEONS = 10;

    public Environment() {
        map = new Map();
    }

    public Map getMap() {
        return map;
    }

    public Tile getFreshestFoodLocation(){
        Date currentDate = new Date();
        Tile freshestTile = null;
        for(int i = 0; i < map.getNbLines();i++){
            for(int j = 0; j < map.getNbTilesInLine(i);j++){
                Tile currentTile = map.getTile(i,j);
                if(currentDate.compareTo(currentTile.getFood().getDate()) < 0){
                    freshestTile = currentTile;
                    currentDate = currentTile.getFood().getDate();
                }
            }
        }
        return freshestTile;
    }

    public boolean isThereFood(){
        for(int i = 0; i < map.getNbLines();i++){
            for(int j = 0; j < map.getNbTilesInLine(i);j++){
                Tile currentTile = map.getTile(i,j);
                if(currentTile.isHasFood()){
                    return true;
                }
            }
        }
        return false;
    }
    public PigeonSet getPigeonSet() {
        return pigeonSet;
    }

    public void createPigeons(){
        Random rng = new Random();
        for(int i = 0; i < NB_PIGEONS; i++){
            pigeonSet.addPigeon(new Pigeon(new Position(Math.abs(rng.nextInt()) % map.getNbLines(),Math.abs(rng.nextInt()) % map.getNbLines()), this));
        }
    }

    public void addPigeon(Pigeon pigeon){
        this.pigeonSet.addPigeon(pigeon);
    }

}
