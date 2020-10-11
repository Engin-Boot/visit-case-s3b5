package visitcounter.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.core.Logger;
// import org.slf4j.Logger;

// import java.lang.System.Logger;
// import java.util.logging.LogManager;

public class FootFallWriter {
    static Logger logger = LogManager.getLogger(FootFallWriter.class);

    public String writeFootfallEntry( String entry )
    {
        logger.info(entry);
        return entry ;
    }
}
