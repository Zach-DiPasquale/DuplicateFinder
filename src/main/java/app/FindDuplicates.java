package app;

import lib.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lib.ReadCSV;

import java.util.*;

@Controller
public class FindDuplicates {

    @GetMapping("/")
    public String index(Model model) {
        ArrayList<Person> duplicates = new ArrayList<>();
        ArrayList<Person> goodEntries = new ArrayList<>();
        model.addAttribute("duplicates", duplicates);
        model.addAttribute("goodEntries", goodEntries);
        HashMap<Integer, Person> entries = new HashMap<>();

        try {
            entries = ReadCSV.readCSV();
        } catch (Exception e) {
            System.out.println(e);
        }

        Set<Integer> goodEntriesSet = new HashSet<>(entries.keySet());
        Set<Integer> duplicatesSet = new HashSet<>();

        for (int i = 0; i <= 100; i++) {
            if(!entries.containsKey(i)) {
                continue;
            }
            for (int j = i+1; j <= 100; j++) {
                if(!entries.containsKey(j)) {
                    continue;
                }

                if (entries.get(i).isDuplicate(entries.get(j))) {
                    duplicatesSet.add(i);
                    duplicatesSet.add(j);
                    goodEntriesSet.remove(i);
                    goodEntriesSet.remove(j);
                    break;
                }

            }
        }
        for (Integer i : duplicatesSet) {
            duplicates.add(entries.get(i));
        }

        for (Integer i : goodEntriesSet) {
            goodEntries.add(entries.get(i));
        }

        return "index";
    }
}
