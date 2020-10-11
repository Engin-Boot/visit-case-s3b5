package visitcounter.reciever;

import java.io.IOException;

public interface IFootFallCsvWriter 
{
    void createWriter(String path) throws IOException;

    // writes column names to csv
    void writeColumnNames(String... column_names);

    // writes Entry to csv
    void writeEntry(String... record);
    void closeOffWriter() throws IOException;    
}
