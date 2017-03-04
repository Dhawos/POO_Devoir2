package pigeons;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dhawo on 03/03/2017.
 */
public class PigeonSet extends Observable {
    private ArrayList<Pigeon> set;


    public PigeonSet() {
        set = new ArrayList<>();
    }

    public ArrayList<Pigeon> getSet() {
        return set;
    }

    public void addPigeon(Pigeon pigeon){
        set.add(pigeon);
        setChanged();
        notifyObservers();
    }

    public void notifyView(){
        setChanged();
        notifyObservers();
        for(Pigeon pigeon : set){
            pigeon.notifyView();
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);

    }
}
