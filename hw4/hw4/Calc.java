// Calc.java


package hw4;
import java.util.Scanner;
import exceptions.EmptyException;

/** A program for an RPN calculator that uses a stack. */
public final class Calc {

    // Hush checkstyle
    private Calc() {}

    public static boolean isInt(String s) {
        try {
            int num = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * The main function.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayStack<String> stack = new ArrayStack<String>();
        String token; // store user commands
        while(scan.hasNext()) { // program cycles until user quits
            token = scan.next(); // get the next token
            if (token.equals("?")) { // print command
                System.out.println(stack.toString());
            }
            else if (isInt(token)) { // user enters an integer
                stack.push(token);
            }
            else if (token.equals("+")) { // add
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second + first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough integers in stack");
                }
            }
            else if (token.equals("-")) { // subtract
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second - first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough integers in stack");
                }
            }
            else if (token.equals("*")) { // multiply
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second * first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough integers in stack");
                }
            }
            else if (token.equals("/")) { // divide
                int second = 0;
                int first = 0;
                try {
                    second = Integer.parseInt(stack.top());
                    stack.pop();
                    first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second / first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough integers in stack");
                }
                catch (ArithmeticException a) { // divide by 0
                    System.err.println("ERROR: Can't Divide by 0");
                    stack.push(Integer.toString(first));
                    stack.push(Integer.toString(second));
                }
            }
            else if (token.equals("%")) { // mod
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second % first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough integers in stack");
                }
            }
            else if (token.equals(".")) { // pop and print top
                System.out.println(stack.top());
                stack.pop();
            }
            else if (token.equals("!")) { // quit
                return;
            }
            else { // none of the recognized tokens
                System.err.println("ERROR: Invalid Input");
            }
        }
    }
}
