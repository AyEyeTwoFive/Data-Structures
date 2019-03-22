// Calc.java


package hw4;

import java.util.Scanner;
import exceptions.EmptyException;

/** A program for an RPN calculator that uses a stack. */
public final class Calc {

    // Hush checkstyle
    private Calc() {}

    /** Method to determine if a string represents an integer.
     *
     * @param s The string to evaluate
     * @return boolean value of whether true or false
     */
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
        Stack<String> stack = new ArrayStack<String>();
        String token; // store user commands
        while (scan.hasNext()) { // program cycles until user quits
            token = scan.next(); // get the next token
            if ("?".equals(token)) { // print command
                System.out.println(stack.toString());
            }
            else if (isInt(token)) { // user enters an integer
                stack.push(token);
            }
            else if ("+".equals(token)) { // add
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second + first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough arguments.");
                }
            }
            else if ("-".equals(token)) { // subtract
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(first - second));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough arguments.");
                }
            }
            else if ("*".equals(token)) { // multiply
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(second * first));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough arguments.");
                }
            }
            else if ("/".equals(token)) { // divide
                int second = 0;
                int first = 0;
                try {
                    second = Integer.parseInt(stack.top());
                    stack.pop();
                    first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(first / second));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough arguments.");
                }
                catch (ArithmeticException e) { // divide by 0
                    System.err.println("ERROR: Divide by 0");
                    stack.push(Integer.toString(first));
                    stack.push(Integer.toString(second));
                }
            }
            else if ("%".equals(token)) { // mod
                try {
                    int second = Integer.parseInt(stack.top());
                    stack.pop();
                    int first = Integer.parseInt(stack.top());
                    stack.pop();
                    stack.push(Integer.toString(first % second));
                }
                catch (EmptyException e) { // there wasn't 2 integers
                    System.err.println("ERROR: Not enough arguments.");
                }
            }
            else if (".".equals(token)) { // pop and print top
                try {
                    System.out.println(stack.top());
                    stack.pop();
                }
                catch (EmptyException e) { // nothing there
                    System.err.println("ERROR: Not enough arguments.");
                }
            }
            else if ("!".equals(token)) { // quit
                return;
            }
            else { // none of the recognized tokens
                System.err.println("ERROR: bad token.");
            }
        }
    }
}
