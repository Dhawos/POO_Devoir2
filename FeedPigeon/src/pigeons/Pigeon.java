package pigeons;

import environment.Environment;
import environment.Position;
import environment.Tile;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by dhawo on 03/03/2017.
 */
public class Pigeon extends Observable implements Observer,Runnable {
    private Position position;
    private Environment env;

    public Pigeon(Environment env) {
        this.position = new Position();
        this.env = env;
    }

    public Pigeon(Position position, Environment env) {
        this.position = position;
        this.env = env;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                if (!this.env.isThereFood()){
                    break;
                }

                Tile freshestFood = this.env.getFreshestFoodLocation();
                if (this.position.getY() < freshestFood.getY()){
                    moveRight();
                }else if(this.position.getY() > freshestFood.getY()){
                    moveLeft();
                }else if(this.position.getX() < freshestFood.getX()){
                    moveDown();
                }else if(this.position.getX() > freshestFood.getX()){
                    moveUp();
                }else{
                    eatFood();
                }
            }catch(Exception ex){

            }
        }
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void notifyView(){
        this.setChanged();
        this.notifyObservers();
    }

    public void moveRight(){
        this.position.setY(this.position.getY() + 1);
        this.setChanged();
        this.notifyObservers();
    }

    public void moveLeft(){
        this.position.setY(this.position.getY() - 1);
        this.setChanged();
        this.notifyObservers();
    }

    public void moveUp(){
        this.position.setX(this.position.getX() - 1);
        this.setChanged();
        this.notifyObservers();
    }

    public void moveDown(){
        this.position.setX(this.position.getX() + 1);
        this.setChanged();
        this.notifyObservers();
    }

    public void eatFood(){
        Tile tileToEat = this.env.getMap().getTile(this.position.getX(), this.position.getY());
        tileToEat.removeFood();
        this.setChanged();
        this.notifyObservers();
    }
}
