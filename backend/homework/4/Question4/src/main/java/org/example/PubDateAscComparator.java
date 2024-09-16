package org.example;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
       if(((Book)o1).getYear()==((Book)o2).getYear())
           return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
       return ((Book)o1).getYear() - ((Book)o2).getYear();
    }
}
