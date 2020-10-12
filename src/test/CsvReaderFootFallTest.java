package visitcounter.sender;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public class CsvReaderFootFallTest {

	@Test(expected = IOException.class)
	public void whenFileIsNotPresentThenThrowException() throws IOException 
	{
		CsvReaderFootFall reader = new CsvReaderFootFall();
		reader.readFootFallData("src/test/resources/missingfile.csv");	
	}
	

	@Test
	public void whenCsvFileIsPresentThenReturnListOfRecords() throws IOException
	{
		CsvReaderFootFall reader = new CsvReaderFootFall();
		List<String> expectedRecords = new ArrayList<>();
		expectedRecords.add("2020-01-01,08:10:01");
		expectedRecords.add("2020-01-01,08:10:30");	
		
		List<String> actualRecords = reader.readFootFallData("src/resources/test.csv");
		Assert.assertArrayEquals(expectedRecords.toArray(), actualRecords.toArray());	
	}

}
