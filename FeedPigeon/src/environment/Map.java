package environment;

import java.util.ArrayList;

/**
 * Created by dhawo on 27/02/2017.
 */
public class Map {
    private static int SIZE = 20;
    private ArrayList<ArrayList<Tile>> data;

    public Map() {
        this.data = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            this.data.add(i,new ArrayList<>(SIZE));
            for(int j = 0; j < SIZE; j++){
                this.data.get(i).add(j, new Tile(i,j));
            }
        }
    }

    public Map(Map other) {
        this.data = new ArrayList<>(other.getNbLines());
        for (int i = 0; i < other.getNbLines(); i++) {
            this.data.add(i,new ArrayList<>(other.getNbLines()));
            for(int j = 0; j < other.getNbLines(); j++){
                this.data.get(i).add(j, new Tile(other.getTile(i,j)));
            }
        }
    }

    public Map(ArrayList<ArrayList<Tile>> data) {
        this.data = data;
    }

    public Tile getTile(int x, int y) throws ArrayIndexOutOfBoundsException{
        if(x < 0 || x > this.data.size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else if(y < 0 || y > this.data.get(x).size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return this.data.get(x).get(y);
        }
    }

    public Tile getTile(Position pos) throws ArrayIndexOutOfBoundsException{
        int x = pos.getX();
        int y = pos.getY();
        return getTile(x,y);
    }

    public int getNbLines(){
        return this.data.size();
    }

    public int getNbTilesInLine(int x){
        if(x < 0 || x > this.data.size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return this.data.get(x).size();
        }
    }
}