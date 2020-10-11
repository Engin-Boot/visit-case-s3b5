package visitcounter.reciever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String args[]) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FootfallReader footfallConsoleReader = new FootfallReader();
        StoreFootfallEntry footFallEntryStorer = new StoreFootfallEntry();
        try {
            footfallConsoleReader.readFoodfallData(footFallEntryStorer, bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
        
    
}
