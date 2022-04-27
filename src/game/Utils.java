package game;

import java.util.ArrayList;
import java.util.Random;
public class Utils {
    private static Random rand= new Random();

    public static double getRandomBias() {
        return rand.nextDouble();
    }

    //new method to use for any percentage based interactions
    public static boolean getRandomBias( int percentageNum ) {
        return rand.nextDouble() <= percentageNum;
    }

    public static int getRandomFrom(ArrayList<Integer> list) {
        int index = rand.nextInt(list.size()-1);
        return list.get(index);
    }
}
