import controller.RunController;
import environment.Environment;
import environment.Tile;
import pigeons.Pigeon;
import pigeons.ScarePigeons;
import view.MainFrame;
import view.TilePanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dhawo on 27/02/2017.
 */
public class Simulator {

    private RunController controller;
    private Environment environment;
    private ArrayList<Thread> threads;

    public static void main(String[] args) {
        Simulator main = new Simulator();
        main.threads = new ArrayList<>();
        main.environment = new Environment();

        main.initControllers();
        main.initView();
        for(int i = 0; i < main.environment.getPigeonSet().getSet().size(); i++){
            main.threads.add(new Thread(main.environment.getPigeonSet().getSet().get(i)));
            main.threads.get(i).start();
        }
        ScarePigeons threadScarePigeons = new ScarePigeons(main.environment);
        main.threads.add(new Thread(threadScarePigeons));
        main.threads.get(main.threads.size()-1).start();
    }

    public void initView() {
        MainFrame mainFrame = new MainFrame(environment.getMap().getNbLines(),environment);
        this.environment.createPigeons();
        for (int i=0;i<environment.getMap().getNbLines();i++) {
            for (int j=0;j<environment.getMap().getNbTilesInLine(i);j++) {
                Tile realTile = environment.getMap().getTile(i,j);
                TilePanel realTilePanel = mainFrame.getRealTileMap().stream().filter(t -> t.getXPos() == realTile.getX() && t.getYPos() == realTile.getY()).findFirst().get();
                realTile.addObserver(realTilePanel);
                this.environment.getPigeonSet().addObserver(realTilePanel);
                for(Pigeon pigeon : this.environment.getPigeonSet().getSet()){
                    pigeon.addObserver(realTilePanel);
                }
            }
        }
        this.environment.getPigeonSet().notifyView();
    }

    public void initControllers() {
        controller = new RunController(environment);
    }
}
