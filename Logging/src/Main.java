import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LoggerTest loggerTest = new LoggerTest();
        try {
            loggerTest.getLog();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
