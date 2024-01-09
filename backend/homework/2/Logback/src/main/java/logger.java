
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class logger{
    private static final Logger slf4jLogger = LoggerFactory.getLogger(logger.class);

    public static void main(String[] args) {
        slf4jLogger.trace("Hello!");
    }
}