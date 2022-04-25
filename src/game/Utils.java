package game;

import java.util.ArrayList;
import java.util.Random;
public class Utils {
    private static Random rand= new Random();

    public static double getRandomBias() {
        return rand.nextDouble();
    }

    public static int getRandomFrom(ArrayList<Integer> list) {
        int index = rand.nextInt(list.size());
        return list.get(index-1);
    }
}
