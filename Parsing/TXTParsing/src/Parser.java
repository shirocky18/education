import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Parser {

     public void parse(String path) {
         try {
             List<String> strings = Files.readAllLines(Paths.get(path));
             for(String str : strings) {
                 String[] line = str.split("\t");
                 if(line.length == 4 & line[1].matches("\\d{2}(.)\\d{2}(.)\\d{4}") & line[2].matches("\\d+")) {
//                     String fragment1 = line[0];
//                     String fragment2 = line[1];
//                     String fragment3 = line[2];
//
//                     Employee em = new Accouter(fragment1, Integer.parseInt(fragment3), string2date(fragment2));
//                     Employee.addToList(em);
                     addToListEmployeeByPosition(line);
                 }
             }
         }
         catch (IOException ioEx) {
             ioEx.printStackTrace();
         }
     }

     private Date string2date(String str) {
         SimpleDateFormat format = new SimpleDateFormat("d.MM.y");
         Date date = new Date();
         try {
             date = format.parse(str);
             format.format(date);
         }
         catch (ParseException ex) {
             ex.printStackTrace();
         }
         return date;
     }

     private void addToListEmployeeByPosition(String[] strings) {
         if(strings[3].equals("Бухгалтер"))
             Employee.addToList(new Accouter(strings[0], Integer.parseInt(strings[2]), string2date(strings[1])));

         if(strings[3].equals("Водитель"))
             Employee.addToList(new Driver(strings[0], Integer.parseInt(strings[2]), string2date(strings[1])));
     }
}
