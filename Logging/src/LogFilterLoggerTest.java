import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LogFilterLoggerTest implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return !record.getMessage().endsWith("t");
    }
}
