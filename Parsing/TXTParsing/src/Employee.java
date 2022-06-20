import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Employee {

    private String name;
    private int salary;
    private Date employmentDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
    private static ArrayList<Employee> list = new ArrayList<>();

    public Employee(String name, int salary, Date date) {
        this.name = name;
        this.salary = salary;
        employmentDate = date;
    }

    public String toString() {
        return this.getClass().getName() + " - " + name + "\nEmploymentDate: " + dateFormat.format(employmentDate) + "\nSalary: " + salary;
    }

    public static void addToList(Employee em) {
        list.add(em);
    }

    public static void listEmployees() {
        list.forEach(System.out::println);
    }
    public String getName() {
        return name;
    }
    public int getSalary() {
        return salary;
    }
    public Date getEmploymentDate() {
        return employmentDate;
    }

    //сортировка

    public static void sortListBySalary() {
        list.sort(comparatorBySalary);
    }
    public static void sortListByName() {
        list.sort(comparatorByName);
    }
    public static void sortListByDate() {
        list.sort(comparatorByDate);
    }

    //Компораторы

    public static Comparator<Employee> comparatorBySalary = Comparator.comparingInt(Employee::getSalary);
    public static Comparator<Employee> comparatorByName = Comparator.comparing(Employee::getName);
    public static Comparator<Employee> comparatorByDate = Comparator.comparing(Employee::getEmploymentDate);
}
