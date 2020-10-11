package visitcounter.reciever;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

public class CalculationsCsvWriter implements IFootFallCsvWriter {
    private CSVWriter csvWriter;

    public CSVWriter getWriter() {
        return csvWriter;
    }

    public void createWriter(String path) throws IOException
    {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(path)) ;
        this.csvWriter = new CSVWriter(bufferedWriter) ;        
    }



    @Override
    public void writeColumnNames(String... columnNames) {
        csvWriter.writeNext(columnNames);

    }

    @Override
    public void writeEntry(String... entry) {
        csvWriter.writeNext(entry);
    }

    @Override
    public void closeOffWriter() throws IOException {
        csvWriter.close();
    }

}
