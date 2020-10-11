package visitcounter.reciever;

import java.io.BufferedReader;
import java.io.IOException;

public class FootfallReader {
    // reads line by line
    public void readFoodfallData(StoreFootfallEntry storeFootfallEntry, BufferedReader bufferedReader)
            throws IOException
    {
        String line ;
        while( ( line = bufferedReader.readLine() ) != null )
        {
            storeFootfallEntry.storeAsObject(line);
        }
        
    }
}
