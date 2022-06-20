
public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parse("docs/FileForParsing.txt");
        Employee.sortListByName();
        Employee.listEmployees();
    }
}
