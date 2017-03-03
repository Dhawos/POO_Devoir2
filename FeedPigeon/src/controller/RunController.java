package controller;

import environment.Environment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

/**
 * Created by dhawo on 27/02/2017.
 */
public class RunController extends MouseAdapter {
    private Environment environment;

    public RunController(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("START")) {

            }
        }
    }
}
