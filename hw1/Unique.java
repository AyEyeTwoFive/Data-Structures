/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * Unique.java
 */

package hw1;
import java.util.*;

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
        Vector<Integer> nums = new Vector<Integer>(); //where we'll store the numbers
        for(int i=0; i < args.length; i++) {
            int thisnum = Integer.parseInt(args[i]);
               if(!(nums.contains(thisnum))) { //if it is unique
                nums.add(thisnum);
            }
        }

        for(int i=0; i<nums.size();i++) { //let's print
            System.out.println(nums.get(i));
        }
    }

}
