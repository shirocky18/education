import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerTest {

    private static final Logger LOG = Logger.getLogger(LoggerTest.class.getName());

    public void getLog() throws IOException {
        Handler fileHandler = new FileHandler("D:/skillbox/Loggers/logger_file");
        fileHandler.setFilter(new LogFilterLoggerTest());
        fileHandler.setFormatter(new LogFormatterLoggerTest());
        LOG.setUseParentHandlers(false);
        LOG.addHandler(fileHandler);

        for(int i = 0; i < 900000; i++) {
            switch (i) {
                case 890000:
                    LOG.info("We got the number - 890000t");
                    break;
                case 730000:
                    LOG.info("We got the number - 730000");
                    break;
                case 150000:
                    LOG.info("We got the number - 150000");
                    break;
                case 870000:
                    LOG.info("We got the number - 870000");
                    break;
            }
//            if (i == 89)
//                LOG.info("We got the number - 89t");
//            else if (i == 73)
//                LOG.info("We got the number - 73");
//            else if (i == 15)
//                LOG.info("We got the number - 15");
        }
    }
}
