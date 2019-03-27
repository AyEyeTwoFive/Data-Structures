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
        // File of 100,000 random integers between 1-100
        FileWriter file1 = new FileWriter("file1.txt");
        PrintWriter writer1 = new PrintWriter(file1);
        for (int i = 0; i < 100000; i++) {
            writer1.print(rand.nextInt(100));
            writer1.print(" ");
        }
        writer1.close();
        file1.close();

        // File of 100,000 random integers between 1-100, 25% 1
        FileWriter file2 = new FileWriter("file2.txt");
        PrintWriter writer2 = new PrintWriter(file2);
        for (int i = 0; i < 100000; i++) {
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

        // File of 100,000 random integers between 1-100, 50% 1
        FileWriter file3 = new FileWriter("file3.txt");
        PrintWriter writer3 = new PrintWriter(file3);
        for (int i = 0; i < 100000; i++) {
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

        // File of 100,000 random integers between 1-100, 75% 1
        FileWriter file4 = new FileWriter("file4.txt");
        PrintWriter writer4 = new PrintWriter(file4);
        for (int i = 0; i < 100000; i++) {
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

        // File of 100,000 1s
        FileWriter file5 = new FileWriter("file5.txt");
        PrintWriter writer5 = new PrintWriter(file5);
        for (int i = 0; i < 100000; i++) {
            writer5.print(1);
            writer5.print(" ");
        }
        writer5.close();
        file5.close();

    }

}

