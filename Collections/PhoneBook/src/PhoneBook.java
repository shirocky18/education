import java.util.*;

public class PhoneBook {

    private HashMap<ArrayList<String>, String> phoneBook = new HashMap<>();
    private boolean isPhoneNumber = false;
    private boolean isFreePhoneNumber = false;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        for(;;) {
            System.out.println("Enter phone number, name or command:");
            String str = scanner.nextLine();
            if(str.equals("END")) {
                System.out.println("Bye!");
                break;
            }
            else if(str.equals("LIST")) {
                listMap(phoneBook);
                continue;
            }
            checkIsNum(str);
            entry(str);
        }
    }

    private void entry(String str) {
        checkIsFree(str);
        String name = "";
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> existingNumList = new ArrayList<>();
        if(isPhoneNumber && isFreePhoneNumber) {
            System.out.println("Enter name:");
            name = scanner.nextLine();
            if(isRepeatName(name)) {
                existingNumList = Objects.requireNonNull(getKeyByValue(phoneBook, name));
                numbers.addAll(existingNumList);
                phoneBook.remove(existingNumList);
            }
            numbers.add(str);
            phoneBook.put(numbers, name);
        }
        else if(!isPhoneNumber) {
            System.out.println("Enter phone number:");
            if(isRepeatName(str)) {
                existingNumList = Objects.requireNonNull(getKeyByValue(phoneBook, str));
                numbers.addAll(existingNumList);
                phoneBook.remove(existingNumList);
            }
            String num = scanner.nextLine();
            checkIsFree(num);
            if(isFreePhoneNumber) {
                numbers.add(num);
                phoneBook.put(numbers, str);
            }
            else {
                phoneBook.put(existingNumList, str);
            }
        }
    }

    private void checkIsNum(String str) {
        if(str.matches("[0-9]+"))
            isPhoneNumber = true;
        else
            isPhoneNumber = false;
    }

    private void checkIsFree(String phoneNum) {
        if(!phoneBook.isEmpty()) {
            for (List<String> strings : phoneBook.keySet()) {
                if (strings.contains(phoneNum)) {
                    isFreePhoneNumber = false;
                    System.out.println("This phone number is registered already.");
                    break;
                } else {
                    isFreePhoneNumber = true;
                }
            }
        }
        else
            isFreePhoneNumber = true;
    }
    private void listMap(Map map) {
        System.out.println("List of phone book:");
        for(Object num : map.keySet()) {
            System.out.println(num + " - " + map.get(num));
        }
    }
    private boolean isRepeatName(String name) {
        boolean isRepeatName = false;
        if(phoneBook.containsValue(name))
            isRepeatName = true;
        return isRepeatName;
    }
    private ArrayList<String> getKeyByValue(Map<ArrayList<String>, String> map, String val) {
        for(ArrayList<String> key : map.keySet()) {
            if(map.get(key).equals(val))
                return key;
        }
        return null;
    }
}
