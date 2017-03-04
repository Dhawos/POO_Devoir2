package environment;

import java.util.Observable;

/**
 * Created by dhawo on 27/02/2017.
 */
public class Tile extends Observable {
    private Position position;
    private Food food = null;


    public Tile(int x, int y) {
        this.position = new Position(x,y);
    }

    public Tile(Position position) {
        this.position = position;
    }

    public Tile(Tile other){
        this.position = new Position(other.getPosition());
        this.food = other.food;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean isHasFood() {
        return food != null;
    }

    public Food getFood() {
        return food;
    }

    public void addFood() {
        food = new Food();
        setChanged();
        notifyObservers();
    }

    public void removeFood() {
        food = null;
        setChanged();
        notifyObservers();
    }

}

