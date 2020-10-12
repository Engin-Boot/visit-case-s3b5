package visitcounter.receiver;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.MissingResourceException;

import org.junit.Assert;
import org.junit.Test;



public class UtilityReceiverTest {
	@Test
	public void whenSplitRecordByCommaThenThowNoException() throws Exception {
		String record = "2020/01/01,10:00:05";
		String[] splitRecord = Utility.CommaSplitData(record);
		
		Assert.assertEquals("2020/01/01", splitRecord[0]);
		Assert.assertEquals("10:00:05", splitRecord[1]);
	}
	
	
	@Test
	public void whenConvertStringToDateIsInvokedThenThrowNoException() throws Exception {
		LocalDate expectedDate = LocalDate.of(2020, 1, 1);
		String date = "2020/01/01";
		LocalDate actualDate = Utility.stringToDate(date);
		
		Assert.assertEquals(expectedDate,actualDate);
	}
	
	@Test(expected = DateTimeParseException.class)
	public void whenConvertStringInInvalidFormatToDateThenThrowException() throws Exception {
		String invalid_date = "2020:01:01"; //Should be in yyyy/MM/dd format
		Utility.stringToDate(invalid_date);
	}
	
	@Test
	public void whenConvertStringToTimeIsInvokedThenThrowNoException() throws Exception {
		LocalTime expectedTime = LocalTime.of(10, 0, 5);
		String time = "10:00:05";
		LocalTime actualTime = Utility.stringToTime(time);
		
		Assert.assertEquals(expectedTime,actualTime);
	}
	
	@Test(expected = DateTimeParseException.class)
	public void whenConvertStringInInvalidFormatToTimeThenThrowException() throws Exception {
		String invalid_time = "10:0:5";  //Should be in HH:mm:ss format
		Utility.stringToDate(invalid_time);
	}
	
	@Test
	public void whenReadCsvFileNameFromPropertiesFileWithValidKeyThenReturnFileNameWithPath(){
		String filePath = Utility.getFilePathOfCsvOutput("filename1");
		Assert.assertEquals("src/resources/test1.csv", filePath);
	}
	
	@Test(expected = MissingResourceException.class)
	public void whenReadCsvFileNameFromPropertiesFileWithInvalidKeyThenThrowException(){
		Utility.getFilePathOfCsvOutput("invalidkey");
	}
	
	@Test
	public void whenGetWorkingHoursOfDayThenThrowNoException() {
		int working_hours[] = Utility.getWorkingHoursForOneDay();
		Assert.assertEquals(9, working_hours[0]); //5
		Assert.assertEquals(17, working_hours[1]); //18
	}
	

}
