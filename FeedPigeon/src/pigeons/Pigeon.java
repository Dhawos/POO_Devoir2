package pigeons;

import environment.Environment;
import environment.Food;
import environment.Position;
import environment.Tile;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by dhawo on 03/03/2017.
 */
public class Pigeon extends Observable implements Observer,Runnable {
    private Position position;
    private Environment env;
    private Random rng = new Random();
    //public boolean flag = false;

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

                //Thread.sleep(Math.abs(rng.nextInt(1000)));
                Thread.sleep(1000);
                while (!this.env.isThereFood()){
                    Thread.sleep(100);
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
                    synchronized (freshestFood.getFood()){
                        eatFood(freshestFood.getFood());
                    }

                }

            }catch(Exception ex){
               ex.printStackTrace();
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    public synchronized void eatFood(Food food){
        Tile tileToEat = this.env.getMap().getTile(this.position.getX(), this.position.getY());
        if (!tileToEat.isHasFood()){
            System.out.println("the thread pigeon" + Thread.currentThread().getName() + " couldn't ate the food on position : (" + this.getPosition().getX() + ", " + this.getPosition().getY() +")");
            return;
        }
        tileToEat.removeFood();
        System.out.println("the thread pigeon" + Thread.currentThread().getName() + " has eaten the food on position : (" + this.getPosition().getX() + ", " + this.getPosition().getY() +")");
        this.setChanged();
        this.notifyObservers(tileToEat);

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
}
