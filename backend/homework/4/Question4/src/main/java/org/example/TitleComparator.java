package org.example;

import java.util.Comparator;

class TitleComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
    }

}