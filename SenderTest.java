package Sender;



import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class SenderTest {

	@Test
	void WhenAskForCsvFilePathReturnTheExpectedCorrectPath() {
		String expectedPath = "FootFallEntries.csv";
		Assert.assertTrue(expectedPath == extractCSVFile.loadFileName());
	}
	@Test
	void WhenValidStringIsPassedToTheMethodThenCorrectDateTimeObjectIsCreated() throws FileNotFoundException, ParseException {
		String testerValue = "02/01/2020,13:23:10";
		Date returnedObj = createDateTimeObject.CreateDateTimeObjectFromData(testerValue);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String returnedObjToString = dateFormat.format(returnedObj);  
		Assert.assertTrue(returnedObjToString.equalsIgnoreCase("02/01/2020 13:23:10"));
	}
	
	@Test
	void WhenExtractionIsDoneThenDataIsRetrieved() throws FileNotFoundException, ParseException {
		ArrayList<Date> dateList;
		dateList = extractDateAndStoreInList.extractDateAndCreateList();
		Assert.assertTrue(dateList.size() !=0);
		Assert.assertTrue(extractDateAndStoreInList.dataPopulated);
	}
	
	@Test
	void WhenValidDateListIsSentThenCorrectOutputIsDisplayed() throws FileNotFoundException, ParseException {
		ArrayList<Date> dateList;
		dateList = extractDateAndStoreInList.extractDateAndCreateList();
		Sender.PrintData(dateList);
		Assert.assertTrue(Sender.isPrinted);
	}
	

}
