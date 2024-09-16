package org.example;

import java.util.*;

public class Main {

    private static Set<Book> treeSetDemo() {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(new PubDateAscComparator());
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            Logging.print(String.valueOf(book));
        }
        return books;
    }

    public static void main(String[] args) {
        treeSetDemo();
    }

}



