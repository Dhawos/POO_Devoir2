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
                if (this.env.isFlagScarePigeons()){
                    goToRandomTile();

                }else if (this.env.isThereFood()){
                    Tile freshestFood = this.env.getFreshestFoodLocation();
                    Food foodToEat = freshestFood.getFood();

                    if (this.position.getY() < freshestFood.getY()){
                        moveRight();
                    }else if(this.position.getY() > freshestFood.getY()){
                        moveLeft();
                    }else if(this.position.getX() < freshestFood.getX()){
                        moveDown();
                    }else if(this.position.getX() > freshestFood.getX()){
                        moveUp();
                    }else{
                        synchronized (foodToEat){
                            eatFood(foodToEat);
                        }

                    }
                }


            }catch(NullPointerException ex){
                try{
                    System.out.println("nullPointerException : " + ex + " Catched");
                    Thread.sleep(1000);
                }catch(Exception exc){
                    exc.printStackTrace();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void goToRandomTile() {

        int direction = Math.abs(rng.nextInt(3));
        if (direction == 0){
            moveUp();
        }
        if (direction == 1){
            moveDown();
        }
        if (direction == 2){
            moveRight();
        }
        if (direction == 3){
            moveLeft();
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
        if(this.position.getY() != this.env.getMap().getNbLines()-1){
            this.position.setY(this.position.getY() + 1);
            this.setChanged();
            this.notifyObservers();
        }

    }

    public void moveLeft(){
        if(this.position.getY() != 0) {
            this.position.setY(this.position.getY() - 1);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void moveUp(){
        if(this.position.getX() != 0) {
            this.position.setX(this.position.getX() - 1);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void moveDown(){
        if(this.position.getX() != this.env.getMap().getNbLines()-1) {
            this.position.setX(this.position.getX() + 1);
            this.setChanged();
            this.notifyObservers();
        }
    }
}
