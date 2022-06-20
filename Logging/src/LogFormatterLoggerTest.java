import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatterLoggerTest extends Formatter {

    @Override
    public synchronized String format(LogRecord record) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date(record.getMillis());
        simpleDateFormat.format(date);
        return simpleDateFormat.format(date) + "\n" + record.getMessage() + "\n\n";
    }
}
