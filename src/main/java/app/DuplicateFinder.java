package app;

import lib.Person;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DuplicateFinder {

    public static Set<Person> findDuplicates(List<Person> peopleEntries) {

        Set<Person> duplicatesSet = new HashSet<>();

        for (int i = 0; i <= peopleEntries.size()-1; i++) {
            for (int j = i+1; j <= peopleEntries.size()-1; j++) {
                Person p1 = peopleEntries.get(i);
                Person p2 = peopleEntries.get(j);
                if (p1.isDuplicate(p2)) {
                    duplicatesSet.add(p1);
                    duplicatesSet.add(p2);
                    break;
                }

            }
        }

        return duplicatesSet;

    }

}
