package game;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class represents a utility class which essentially generates a random number
 * which is used to account for probability in the game
 *
 * @author: Syed Zubin Hafiz
 * @version: 1.0.0
 * @see: edu.monash.fit2099.game
 */
public class Utils {
    /**
     * Private Attribute
     */

    /**
     * A static variable Random which represents an instance of the Random java.util class
     */
    private static Random rand= new Random();

    /**
     * This method returns a random double number
     * @return a random double number from 0 to 1
     */
    public static double getRandomBias() {
        return rand.nextDouble();
    }

    /**
     * A new method to use for any percentage based interactions
     * @param percentageNum
     * @return
     */
    public static boolean getRandomBias( int percentageNum ) {
        return rand.nextDouble() <= percentageNum;
    }

    /**
     * This method is used to get a random integer from an arrayList of integers
     * @param list
     * @return a number represented by a random index within the list
     */
    public static int getRandomFrom(ArrayList<Integer> list) {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
