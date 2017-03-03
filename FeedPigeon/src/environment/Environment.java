package environment;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by dhawo on 27/02/2017.
 */

public class Environment {
    public static int DEFAULT_TICKRATE = 20;//Number of evaluation per minute
    private Map map;
    private boolean isGameRunning;
    private Random rng;

    public Environment() {
        map = new Map();
        isGameRunning = false;
        rng = new Random();
    }

    public Map getMap() {
        return map;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

}
