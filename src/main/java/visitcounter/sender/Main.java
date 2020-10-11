package visitcounter.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
// import antlr.collections.List;
import java.util.List;

import java.util.ResourceBundle;

public class Main {
    static final Logger LOGGER = LogManager.getLogger(Main.class);
    static boolean IsMainExecuting = false;

    public static ResourceBundle rBundle = ResourceBundle.getBundle("filepath");

    public static String getCsvFileName(String key) {
        String path = rBundle.getString(key);
        return path;
    }

    public static String checkKey(String args[]) {
        String key = "filename";
        if (args.length != 0 && args[0].equals("test_invalidfile")) {
            key = "filename_invalid";
        }
        return key;
    }

    public static void main(String[] args) throws IOException
    {
        String key = checkKey(args) ;
        IsMainExecuting = false ;

        CsvReaderFootFall csvReaderFootFall = new CsvReaderFootFall() ;
        List<String> footFallEntries = csvReaderFootFall.readFootFallData(getCsvFileName(key));
        IFootFallWriter writer = (IFootFallWriter) new FootFallWriter();
        SimulatorLogic logic = new SimulatorLogic( writer ) ;
        logic.sendFootFallData(footFallEntries);
        IsMainExecuting = true ;   

    }
}
