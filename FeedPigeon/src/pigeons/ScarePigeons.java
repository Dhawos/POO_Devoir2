package pigeons;

import environment.Environment;
import java.util.Random;

/**
 * Created by edouard on 04/03/2017.
 */
public class ScarePigeons implements Runnable {
    private Environment env;
    private Random rng = new Random();

    public ScarePigeons(Environment env) {
        this.env = env;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
                if(Math.abs(rng.nextInt(50)) > Math.abs(rng.nextInt(100))){
                    System.out.println("flag scarePigeons true!");
                    this.env.setFlagScarePigeons(true);
                    Thread.sleep(6000);
                    this.env.setFlagScarePigeons(false);
                    System.out.println("flag scarePigeons false!");
                    Thread.sleep(6000);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }


    }
}
