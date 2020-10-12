package visitcounter.receiver;


import java.time.format.DateTimeParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StoreFootfallEntryTest {

	@Test
	public void whenValidFootFallRecordStoredThenNoException() throws Exception
	{
		String record_one = "2020/01/01,10:00:05";
		String record_two = "2020/01/02,11:10:05";
		StoreFootfallEntry storer = new StoreFootfallEntry();
		storer.storeAsObject(record_one);
		storer.storeAsObject(record_two);
		List<FootfallModel> records = storer.getFootfallEntries();
		Assert.assertEquals(2, records.size());
	}
	
	@Test(expected = DateTimeParseException.class)
	public void whenFootFallRecordStoredWithInvalidDateTimeFormatThenThrowException() throws Exception
	{
		String record_one = "2020-01/01,10:00:05"; //invalid record as format is not correct
		StoreFootfallEntry storer = new StoreFootfallEntry();
		storer.storeAsObject(record_one);
	}
	
	@Test(expected = Exception.class)
	public void whenFootFallRecordStoredWithNoCommaSeparatorThenThrowException() throws Exception
	{
		String record_one = "2020-01/0110:00:05"; //invalid record as format is not correct
		StoreFootfallEntry storer = new StoreFootfallEntry();
		storer.storeAsObject(record_one);
	}


}
