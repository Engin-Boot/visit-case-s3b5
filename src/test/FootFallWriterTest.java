package visitcounter.sender;



import org.junit.Assert;
import org.junit.Test;

public class FootFallWriterTest {

	@Test
	public void whenRecordIsWrittenToConsoleThenReturnRecord()
	{
		IFootFallWriter writer = new FootFallWriter();
		String expectedRecord = "2020-01-01,10:00:05";
		String actualRecord = writer.writeFootfallEntry(expectedRecord);
		
		Assert.assertEquals(expectedRecord, actualRecord);
	}
}
