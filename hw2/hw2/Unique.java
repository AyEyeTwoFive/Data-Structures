/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * Unique.java
 */

package hw2;

//import java.util.Iterator;
import java.util.Scanner;

/** Unique problem using a SparseArray and processing from standard in. */
public final class Unique {

    // make checkstyle happy
    private Unique() {}

    /**
     * Print only unique integers out of entered numbers.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SimpleArray<Integer> nums = new SimpleArray<Integer>(10, 0);
        int lastind = 0;
        while (scan.hasNext()) {
            try {
                int thisnum = Integer.parseInt(scan.next());
                int isunique = 0;
                for (int q = 0; q < lastind; q++) {
                    if (thisnum == nums.get(q)) {
                        isunique = 1;
                    }
                }
                if (isunique == 0)   { //if it is unique
                    if (lastind + 2 > nums.length()) { //let's double the size
                        SimpleArray<Integer> temp = nums;
                        nums = new SimpleArray<Integer>(temp.length() * 2, 0);
                        for (int l = 0; l < temp.length(); l++) {
                            nums.put(l, temp.get(l));
                        }
                    }
                    nums.put(lastind, thisnum);
                    lastind = lastind + 1;
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("arguments must be ints");
            }
        }
        for (int i = 0; i < lastind; i++) { //let's print
            System.out.println(nums.get(i));
        }
    }
}
