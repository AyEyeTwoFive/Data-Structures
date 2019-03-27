package hw6;

import java.util.Scanner;

/**
 * Filter unique integers from standard input to standard output.
 *
 * This version uses a PriorityQueue - solution to assignment 7.
 *
 * If you're benchmarking this program, you may want to suppress
 * the output by redirecting it to /dev/null. Also note that the
 * Scanner class is horribly inefficient, alas it's the simplest
 * choice here.
 */
public final class UniquePQ {
    private static PriorityQueue<Integer> data;

    // Make checkstyle happy.
    private UniquePQ() {}

    /**
     *  Main method.
     *  @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        data = new ListPriorityQueue<Integer>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            try {
                int i = Integer.parseInt(s);
                data.insert(i);
            } catch (NumberFormatException e) {
                System.err.printf("Ignored non-integer %s\n", s);
            }
        }

        int val = 0;
        while (!data.empty()) {
            val = data.best();
            data.remove();
            System.out.println(val);
            while (!data.empty() && data.best().equals(val)) {
                data.remove();
            }
        }
    }
}
