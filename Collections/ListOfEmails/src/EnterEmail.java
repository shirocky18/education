import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EnterEmail {

    private boolean isEnd = true;
    private String[] allowableCommands = new String[]{"ADD", "LIST", "END"};
    private TreeSet<Email> emails = new TreeSet<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(isEnd) {
            System.out.println("Enter command");
            String str = scanner.nextLine();
            if (!check(str)) {
                System.out.println("incorrect command");
                continue;
            }
            else {
                String command = getCommand(str);
                implementCommand(str,command);
            }
        }
    }

    private boolean check(String str) {
        boolean checked = false;
        for(String allowableCom : allowableCommands) {
            if(str.startsWith(allowableCom))
                checked = true;
        }
        return checked;
    }

    private String getCommand(String str) {
        String command = "";
        for(String string : allowableCommands) {
            if(str.startsWith(string))
                command = string;
        }
        return command;
    }

    private void implementCommand(String str, String command) {
        if(command.equals(allowableCommands[0])) {
            if(checkEmail(str.substring(4))) {
                emails.add(new Email(str.substring(4)));
                System.out.println("Mail has been added");
            }
        }
        else if(command.equals(allowableCommands[1])) {
            System.out.println("List of emails: ");
            list(emails);
        }
        else if (command.equals(allowableCommands[2])){
            isEnd = false;
            System.out.println("Bye!");
        }
    }

    private void list(Set<Email> set) {
        for(Email email : set) {
            System.out.println(email.getEmail());
        }
    }

    private boolean checkEmail(String email) {
        boolean checked = false;
        if(email.contains("@"))
            checked = true;
        else
            System.out.println("Incorrect format email's");
        return checked;
    }

}
