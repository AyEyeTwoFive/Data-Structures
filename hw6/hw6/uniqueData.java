package hw6;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw6.Position;
import hw6.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;
import java.util.Random;


public class uniqueData {

    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        // File of 30000 random integers between 1-100
        FileWriter file1 = new FileWriter("file1.txt");
        PrintWriter writer1 = new PrintWriter(file1);
        for (int i = 0; i < 30000; i++) {
            writer1.print(rand.nextInt(100));
            writer1.print(" ");
        }
        writer1.close();
        file1.close();

        // File of 30000 random integers between 1-100, 25% 1
        FileWriter file2 = new FileWriter("file2.txt");
        PrintWriter writer2 = new PrintWriter(file2);
        for (int i = 0; i < 30000; i++) {
            int flip = rand.nextInt(4);
            if (flip == 0) {
                writer2.print(rand.nextInt(100));
                writer2.print(" ");
            }
            else {
                writer2.print(1);
                writer2.print(" ");
            }
        }
        writer2.close();
        file2.close();

        // File of 30000 random integers between 1-100, 50% 1
        FileWriter file3 = new FileWriter("file3.txt");
        PrintWriter writer3 = new PrintWriter(file3);
        for (int i = 0; i < 30000; i++) {
            int flip = rand.nextInt(4);
            if (flip == 0 || flip == 1) {
                writer3.print(rand.nextInt(100));
                writer3.print(" ");
            }
            else {
                writer3.print(1);
                writer3.print(" ");
            }
        }
        writer3.close();
        file3.close();

        // File of 30000 random integers between 1-100, 75% 1
        FileWriter file4 = new FileWriter("file4.txt");
        PrintWriter writer4 = new PrintWriter(file4);
        for (int i = 0; i < 30000; i++) {
            int flip = rand.nextInt(4);
            if (flip == 0 || flip == 1 || flip == 2) {
                writer4.print(rand.nextInt(100));
                writer4.print(" ");
            }
            else {
                writer4.print(1);
                writer4.print(" ");
            }
        }
        writer4.close();
        file4.close();

        // File of 30000 1s
        FileWriter file5 = new FileWriter("file5.txt");
        PrintWriter writer5 = new PrintWriter(file5);
        for (int i = 0; i < 30000; i++) {
            writer5.print(1);
            writer5.print(" ");
        }
        writer5.close();
        file5.close();

        // File of 30000 random integers between 1-100, 75% 100
        FileWriter file6 = new FileWriter("file6.txt");
        PrintWriter writer6 = new PrintWriter(file6);
        for (int i = 0; i < 30000; i++) {
            int flip = rand.nextInt(4);
            if (flip == 0 || flip == 1 || flip == 2) {
                writer6.print(rand.nextInt(100));
                writer6.print(" ");
            }
            else {
                writer6.print(100);
                writer6.print(" ");
            }
        }
        writer6.close();
        file6.close();

        // File of 30000 100s
        FileWriter file7 = new FileWriter("file7.txt");
        PrintWriter writer7 = new PrintWriter(file7);
        for (int i = 0; i < 30000; i++) {
            writer7.print(100);
            writer7.print(" ");
        }
        writer7.close();
        file7.close();

        FileWriter file8 = new FileWriter("file8.txt");
        PrintWriter writer8 = new PrintWriter(file8);
        for (int i = 30000; i > 0; i--) {
            writer8.print(i);
            writer8.print(" ");
        }
        writer8.close();
        file8.close();

        FileWriter file9 = new FileWriter("file9.txt");
        PrintWriter writer9 = new PrintWriter(file9);
        for (int i = 0; i < 30000; i++) {
            writer9.print(i);
            writer9.print(" ");
        }
        writer9.close();
        file9.close();

    }

}

