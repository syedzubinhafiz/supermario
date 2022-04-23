package game;

import java.util.Random;
public class Utils {
    private static Random rand= new Random();

    public double getRandomBias() {
        return rand.nextDouble();
    }
}
