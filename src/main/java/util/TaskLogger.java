package util;

import config.Configuration;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.*;

public class TaskLogger {
    private static final Logger logger;

    static {
        logger = Logger.getLogger("LoggerTask" + Configuration.instance.taskToRun.getNumber());
        // We don't want console output.
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        FileHandler fh;
        try {
            fh = new FileHandler("scenario_" + padLeft(Configuration.instance.taskToRun.getNumber(), 2) + ".log");
        } catch (IOException e) {
            throw new RuntimeException("Was not able to access the logging file.");
        }
        fh.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss,SSS").withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());
                return "[" + record.getLevel() + "] " + formatter.format(record.getInstant()) + " - " + record.getMessage() + "\n\n";
            }
        });
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);
    }

    private static String padLeft(int number, int numChars) {
        String ret = String.valueOf(number);
        if (ret.length() < numChars) {
            ret = "0".repeat(numChars - ret.length()) + ret;
        }
        return ret;
    }

    public static Logger getLogger() {
        return logger;
    }
}
