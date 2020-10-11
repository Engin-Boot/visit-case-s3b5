package visitcounter.sender;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReaderFootFall {
    
    // reads data line by line from CSV
    public List<String> readFootFallData( String filename ) throws IOException
    {
        List<String> footFallData = new ArrayList<String>() ;
        
        BufferedReader bReader = Files.newBufferedReader(Paths.get(filename) ) ;
        bReader.readLine() ;
        String line ;
        while( (line = bReader.readLine()) != null ) 
        {
            footFallData.add(line) ;
        }

        return footFallData ;
    }

}
