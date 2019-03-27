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
        String d[] = {"Zion", "Tom", "Karen", "Ed", "Billy", "Beverly"};
        for (int i = d.length-1; i >= 0; i--) {
            unit.insert(d[i]);
        }

        System.out.println(unit.get(0));
        System.out.println(unit.get(1));
        System.out.println(unit.get(2));
        System.out.println(unit.get(3));
        System.out.println(unit.get(4));
        System.out.println(unit.get(5));
        System.out.println(unit.get(6));

        unit.remove();
        System.out.println("removed");

        System.out.println(unit.get(0));
        System.out.println(unit.get(1));
        System.out.println(unit.get(2));
        System.out.println(unit.get(3));
        System.out.println(unit.get(4));
        System.out.println(unit.get(5));
        System.out.println(unit.get(6));

        unit.remove();
        System.out.println("removed");

        System.out.println(unit.get(0));
        System.out.println(unit.get(1));
        System.out.println(unit.get(2));
        System.out.println(unit.get(3));
        System.out.println(unit.get(4));
        System.out.println(unit.get(5));
        System.out.println(unit.get(6));

        //for (String s: d) {
          //  assertEquals(s, unit.best());
            //unit.remove();
        //}

    }

}

