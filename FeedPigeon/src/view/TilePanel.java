package view;

import environment.Position;
import environment.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dhawo on 27/02/2017.
 */
public class TilePanel extends JPanel implements Observer {

    private int xPos;
    private int yPos;
    private JLabel label = new JLabel("");

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }


    public TilePanel(int x, int y) {
        xPos = x;
        yPos = y;
        add(label);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.black));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Tile){
            Tile tile = (Tile)o;
            label.setText("");
        }
        if(arg instanceof Position){
            Position robotPosition = (Position)arg;
            String text = label.getText();

            if(getBackground() == Color.BLUE){
                this.setBackground(Color.WHITE);
            }
            if(robotPosition.getX() == xPos && robotPosition.getY() == yPos){
                setBackground(Color.BLUE);
            }

        }
    }
}
