package view;

import environment.Environment;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dhawo on 27/02/2017.
 */

public class MainFrame extends JFrame implements Observer {

    static final String TITLE = "Feed Pigeon Simulator";
    private JPanel[][] gamePanelSquares = null;
    private ArrayList<TilePanel> realTileMap;
    private int size;
    private Environment env;
    public MainFrame(int size, Environment env) {
        super(TITLE);
        this.size = size;
        this.env = env;
        gamePanelSquares = new JPanel[size][size];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout(0, 0));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        JPanel centerPanel = new JPanel(new GridLayout(1, 0));

        realTileMap = createBoard(centerPanel, "Board");

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);

    }

    private ArrayList<TilePanel> createBoard(JPanel parentPanel, String boardName) {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        //boardPanel.setBorder(new EmptyBorder(2, 2, 2, 2));

        ArrayList<TilePanel> tileMap = new ArrayList<>(size*size);

        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                JPanel panel;
                panel = new TilePanel(i, j, env );
                tileMap.add((TilePanel) panel);
                //addMouseListener((TilePanel)panel);
                gamePanelSquares[i][j] = panel;
            }
        }

        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                boardPanel.add(gamePanelSquares[i][j]);
            }
        }

        gamePanel.add(boardPanel, BorderLayout.CENTER);

        JLabel label = new JLabel(boardName);
        label.setHorizontalAlignment(JLabel.CENTER);
        gamePanel.add(label, BorderLayout.SOUTH);

        parentPanel.add(gamePanel);

        return tileMap;

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public ArrayList<TilePanel> getRealTileMap() {
        return realTileMap;
    }

}
