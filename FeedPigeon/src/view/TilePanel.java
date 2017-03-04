package view;

import environment.Environment;
import environment.Position;
import environment.Tile;
import pigeons.Pigeon;
import pigeons.PigeonSet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dhawo on 27/02/2017.
 */
public class TilePanel extends JPanel implements Observer,MouseListener {

    private int xPos;
    private int yPos;
    private Environment env;
    private JLabel label = new JLabel("");

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }


    public TilePanel(int x, int y, Environment env) {
        xPos = x;
        yPos = y;
        this.env = env;
        add(label);
        addMouseListener(this);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.black));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Tile){
            Tile tile = (Tile)o;
            if(tile.isHasFood()){
                label.setText("F");
            }else{
                label.setText("");
            }
        }
        else{
            PigeonSet set = env.getPigeonSet();
            boolean pigeonFound = false;
            for(Pigeon pigeon : set.getSet()){
                if(pigeon.getPosition().getX() == xPos && pigeon.getPosition().getY() == yPos){
                    setBackground(Color.BLUE);
                    pigeonFound = true;
                    break;
                }
            }
            if(!pigeonFound){
                setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Tile tile = env.getMap().getTile(xPos,yPos);
        tile.addFood();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
