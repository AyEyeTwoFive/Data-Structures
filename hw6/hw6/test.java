package hw6;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw6.Position;
import hw6.LinkedList;


public class test {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        System.out.println(list.first(two));
        Position<String> beforeTwo = list.previous(two);
    }
}

