package environment;

import java.util.Observable;

/**
 * Created by dhawo on 27/02/2017.
 */
public class Tile extends Observable {
    private Position position;
    private boolean hasFood = false;

    public Tile(int x, int y) {
        this.position = new Position(x,y);
    }

    public Tile(Position position) {
        this.position = position;
    }

    public Tile(Tile other){
        this.position = new Position(other.getPosition());
        this.hasFood = other.isHasFood();
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
        return hasFood;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
        setChanged();
        notifyObservers();
        //System.out.println("Dirt has been set on tile (" + position.getX() + "," + position.getY()+ ").");
    }

}

