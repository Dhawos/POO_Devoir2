package pigeons;

import environment.Environment;
import environment.Position;

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
                moveRight();
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
}
