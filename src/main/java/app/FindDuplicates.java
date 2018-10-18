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

        List<Person> goodEntries;

        try {
            goodEntries = ReadCSV.readCSV();
        } catch (Exception e) {
            System.out.println(e);
            goodEntries = new ArrayList<>();
        }


        Set<Person> duplicateSet = DuplicateFinder.findDuplicates(goodEntries);

        Set<Person> goodEntriesSet = new HashSet<>(goodEntries);

        for (Person p : duplicateSet) {
            if (goodEntries.contains(p)){
                goodEntries.remove(p);
            }
        }

        ArrayList<Person> duplicates = new ArrayList<>(duplicateSet);

        model.addAttribute("goodEntries", goodEntries);
        List<Person> lp = new ArrayList<>(duplicateSet);
        Collections.sort(lp);
        model.addAttribute("duplicates", lp);


        return "index";
    }
}
