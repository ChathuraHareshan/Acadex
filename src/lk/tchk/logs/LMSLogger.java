package lk.tchk.logs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;

public class LMSLogger {

    public static Logger getLogger(String moduleName) {
        Logger logger = Logger.getLogger(moduleName);
        logger.setUseParentHandlers(false);

        try {
            Files.createDirectories(Paths.get("logs"));

            // Only add handler if not already added
            if (logger.getHandlers().length == 0) {
                FileHandler fh = new FileHandler("logs/" + moduleName + ".log", true);
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}
