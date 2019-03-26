package hw6;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw6.Position;
import hw6.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;

public class test {

    /* Testing Previous method exception in linked list
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        System.out.println(list.first(two));
        Position<String> beforeTwo = list.previous(two);
    }*/

    public static void main(String[] args) {
        BinaryHeapPriorityQueue<String> unit = new BinaryHeapPriorityQueue<String>();
        unit.insert("Paul");


        unit.insert("Peter");
        System.out.println("After Peter");
        System.out.println(unit.get(0));
        System.out.println(unit.get(1));
        System.out.println(unit.get(2));



        unit.insert("Mary");
        System.out.println("After Mary");
        System.out.println(unit.get(0));
        System.out.println(unit.get(1));
        System.out.println(unit.get(2));
        System.out.println(unit.get(3));
    }

}

