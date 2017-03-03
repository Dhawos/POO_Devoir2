import controller.RunController;
import environment.Environment;
import environment.Tile;
import view.MainFrame;
import view.TilePanel;

import java.awt.*;

/**
 * Created by dhawo on 27/02/2017.
 */
public class Simulator {

    private RunController controller;
    private Environment environment;

    public static void main(String[] args) {
        Simulator main = new Simulator();

        main.environment = new Environment();
        main.initControllers();
        main.initView();
    }

    public void initView() {
        MainFrame mainFrame = new MainFrame(environment.getMap().getNbLines());

        for (int i=0;i<environment.getMap().getNbLines();i++) {
            for (int j=0;j<environment.getMap().getNbTilesInLine(i);j++) {
                Tile realTile = environment.getMap().getTile(i,j);
                TilePanel realTilePanel = mainFrame.getRealTileMap().stream().filter(t -> t.getXPos() == realTile.getX() && t.getYPos() == realTile.getY()).findFirst().get();
                realTile.addObserver(realTilePanel);
                if(i == 0 && j == 0){
                    realTilePanel.setBackground(Color.BLUE);
                }
            }
        }
    }

    public void initControllers() {
        controller = new RunController(environment);
    }
}
