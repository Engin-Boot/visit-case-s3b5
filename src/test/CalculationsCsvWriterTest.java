package visitcounter.receiver;


import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;



public class CalculationsCsvWriterTest {

	
	@Test
	public void whenCsvWriterIsCreatedThenThrowNoException() throws IOException
	{
		String path = "src/test/resources/test1.csv";
		CalculationsCsvWriter footFallCsvWriter = new CalculationsCsvWriter();
		footFallCsvWriter.createWriter(path);
		Assert.assertNotNull(footFallCsvWriter.getWriter());
	}
	
	@Test(expected = IOException.class)
	public void whenCsvWriterIsCreatedWithInvalidPathThenThrowException() throws IOException
	{
		String invalid_path = "[invalid_path]/invalid";
		CalculationsCsvWriter footFallCsvWriter = new CalculationsCsvWriter();
		footFallCsvWriter.createWriter(invalid_path);
	}
	
	@Test
	public void whenDataisWrittenToCsvFileThenFileShouldNotBeEmpty() throws IOException
	{
		String column_names[] = {"Column1","Column2"};
		String record[] = {"2020-01-01","10:00:00"};
		String path = "src/test/resources/test1.csv";
		CalculationsCsvWriter footFallCsvWriter = new CalculationsCsvWriter();
		footFallCsvWriter.createWriter(path);
		footFallCsvWriter.writeColumnNames(column_names);
		footFallCsvWriter.writeEntry(record);
		footFallCsvWriter.closeOffWriter();
		
		File file = new File("src/test/resources/test1.csv");
		Assert.assertTrue(file.length()>0);
		
	}
	
}
