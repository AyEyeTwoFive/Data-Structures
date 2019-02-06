/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * Unique.java
 */

package hw1;
import java.util.Arrays;

/** A class with a main method for printing out unique numbers. */
public final class Unique {

    // Make checkstyle happy.
    private Unique() {
        throw new AssertionError("Can not instantiate class Unique\n");
    }

    /**
     * A main method to print the unique numerical command line arguments.
     * @param args The string array of arguments in the command line.
     */
    public static void main(String[] args) {
        int[] nums = new int[args.length]; //where we'll store the numbers
        int lastind = 0;
        for(int i=0; i < args.length; i++) {
            try {
                int thisnum = Integer.parseInt(args[i]);
                if (!(Arrays.stream(nums).anyMatch(j -> j == thisnum))) { //if it is unique
                    nums[lastind] = thisnum;
                    lastind = lastind + 1;
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("arguments must be strings");
            }
        }

        for(int i=0; i<lastind;i++) { //let's print
            System.out.println(nums[i]);
        }
    }

}
