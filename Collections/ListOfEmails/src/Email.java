public class Email implements Comparable<Email> {

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(Email e) {
        return this.email.compareTo(e.getEmail());
    }

}
