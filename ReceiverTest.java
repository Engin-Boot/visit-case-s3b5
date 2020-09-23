package Receiver;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Sender.createDateTimeObject;

class ReceiverTest{
	@Test
	void WhenAskForCsvFilePathReturnTheExpectedCorrectPath() {
		String expectedPath = "C:\\Users\\320090269\\eclipse-workspace\\SenderPackageOutput.csv";
		Assert.assertTrue(expectedPath.equalsIgnoreCase(DataToCSV.GetFilePath()));
	}
	
	@Test
	void WhenCorrectDateIsGivenThenDailyCountHashmapUpdatedCorrectly() throws FileNotFoundException, ParseException {
		String testerValue = "02/01/2020,13:23:10";
		Date testDate = createDateTimeObject.CreateDateTimeObjectFromData(testerValue); 
		LocalDateTime convertedDate=testDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		CountSetters.SetDayCount(convertedDate);
		Assert.assertTrue(CountSetters.setDayCountFunctionCheck);
	}
	
	@Test
	void WhenCorrectDateIsGivenThenHourlyCountHashmapUpdatedCorrectly() throws FileNotFoundException, ParseException {
		String testerValue = "02/01/2020,13:23:10";
		Date testDate = createDateTimeObject.CreateDateTimeObjectFromData(testerValue); 
		LocalDateTime convertedDate=testDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		CountSetters.SetHourCount(convertedDate);
		Assert.assertTrue(CountSetters.setHourCountFunctionCheck);
	}

}
