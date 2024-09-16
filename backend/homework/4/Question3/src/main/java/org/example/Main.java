package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
    public static final <T> void swap (T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static final <T> void swap (List<T> l, int i, int j) {
        Collections.<T>swap(l, i, j);
    }

    public static void main(String...args)
    {
        String [] a = {"Hello", "Goodbye"};
        swap(a, 0, 1);
        Logging.print("a:".concat(Arrays.toString(a)));
        List<String> l = new ArrayList<String>(Arrays.asList(a));
        swap(l, 0, 1);
        Logging.print("l:"+l);
    }

}