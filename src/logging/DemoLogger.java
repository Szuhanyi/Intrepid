package logging;

import java.io.IOException;
import java.util.logging.*;

public class DemoLogger  {
// maybe later.. this should have a lower priority for now... just like that
    public DemoLogger() {
        FileHandler fh = null;
        LogManager lm = LogManager.getLogManager();
        Logger logger = Logger.getLogger("DemoLogger");
        lm.addLogger(logger);
        try {
            fh = new FileHandler("log_est.txt");
            fh.setFormatter(new XMLFormatter());

            logger.addHandler(fh);
            logger.setLevel(Level.INFO);
            logger.log(Level.INFO, "test 1");
            logger.log(Level.INFO, "test 2");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fh != null) {
                fh.close();
            }
        }

    }

}

