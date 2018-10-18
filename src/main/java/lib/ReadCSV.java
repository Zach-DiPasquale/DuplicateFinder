package lib;

import java.io.*;
import java.util.*;

import lib.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadCSV {

    public static List<Person> readCSV() throws FileNotFoundException, IOException {
        String filename = "./src/main/resources/Validity_take_home_exercise/advanced.csv";
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
        File file = new File(filename);

        BufferedReader br = new BufferedReader(new FileReader(file));
        CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT);

        List<Person> entries = new ArrayList<>();

        for (CSVRecord record : csvParser) {
            if (record.get(0).contains("id")){
                continue;
            }

            String csvString = "";
            for(int i = 0; i < 12; i++) {
                String value = record.get(i);
                if (csvString.contains(",")) {
                    csvString += "\"" + value + "\",";
                } else {
                    csvString += value + ",";
                }
            }

            Person p = new Person(Integer.parseInt(record.get(0)), record.get(1), record.get(2),record.get(3), record.get(4),
                    record.get(5), record.get(6),record.get(7), record.get(8), record.get(9), record.get(10), record.get(11),
                    csvString.substring(0, csvString.length()-1));

            entries.add(p);
        }

        return entries;
    }
}
