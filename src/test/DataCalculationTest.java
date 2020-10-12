package visitcounter.receiver;


import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

//import visitcounter.receiver.CalculationsCsvWriter;
//import visitcounter.receiver.FootfallModel;



@RunWith(MockitoJUnitRunner.class)
public class DataCalculationTest {

	@Mock
	CalculationsCsvWriter writer;
	
	ArgumentCaptor<String> argumentCaptorFilePath = ArgumentCaptor.forClass(String.class);


	@Test
	public void whenCalculateAverageFootfallsPerHourOverDayThenThrowNoException() throws IOException
	{
		FootfallModel model1 = new FootfallModel(LocalTime.now(), LocalDate.now());
		FootfallModel model2 = new FootfallModel(LocalTime.of(6, 5, 30),LocalDate.of(2020, 1, 1));
		List<FootfallModel> listRecords = new ArrayList<FootfallModel>();
		listRecords.add(model1);
		listRecords.add(model2);
		
		DataCalculation.avgFootfallsPerHourInADay(listRecords, writer);
		verify(writer, times(1)).createWriter(argumentCaptorFilePath.capture());
		verify(writer, times(1)).writeColumnNames(Matchers.<String>anyVararg());
		verify(writer, atLeastOnce()).writeEntry(Matchers.<String>anyVararg());
		verify(writer, times(1)).closeOffWriter();
		
		Assert.assertEquals("src/resources/test1.csv", argumentCaptorFilePath.getValue());
	}
	
	@Test
	public void whenCalculateDailyFootfallsOverWeekThenThrowNoException() throws IOException
	{
		FootfallModel model1 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 1, 1));
		FootfallModel model2 = new FootfallModel(LocalTime.of(6, 5, 40), LocalDate.of(2020, 1, 1));
		FootfallModel model3 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 1, 2));
		List<FootfallModel> listRecords = new ArrayList<FootfallModel>();
		listRecords.add(model1);
		listRecords.add(model2);
		listRecords.add(model3);
		
		DataCalculation.dailyFootfallInAWeek(listRecords, writer);
		verify(writer, times(1)).createWriter(argumentCaptorFilePath.capture());
		verify(writer, times(1)).writeColumnNames(Matchers.<String>anyVararg());
		verify(writer, atLeastOnce()).writeEntry(Matchers.<String>anyVararg());
		verify(writer, times(1)).closeOffWriter();
		
		Assert.assertEquals("src/resources/test2.csv", argumentCaptorFilePath.getValue());
	}
	

	@Test
	public void whenCalculatePeakDailyFootfallsInParticularMonthThenThrowNoException() throws IOException
	{
		FootfallModel model1 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 1, 31));
		FootfallModel model2 = new FootfallModel(LocalTime.of(6, 5, 40), LocalDate.of(2020, 2, 1));
		FootfallModel model3 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 2, 2));
		List<FootfallModel> listRecords = new ArrayList<FootfallModel>();
		listRecords.add(model1);
		listRecords.add(model2);
		listRecords.add(model3);
		
		DataCalculation.dailyPeakFootfallsInAMonth(2, 2020, listRecords, writer);
		verify(writer, times(1)).createWriter(argumentCaptorFilePath.capture());
		verify(writer, times(1)).writeColumnNames(Matchers.<String>anyVararg());
		verify(writer, times(1)).writeEntry(Matchers.<String>anyVararg());
		verify(writer, times(1)).closeOffWriter();
		
		Assert.assertEquals("src/resources/test3.csv", argumentCaptorFilePath.getValue());
	}
	
	@Test
	public void whenCalculatePeakDailyFootfallsInLastMonthThenThrowNoException() throws IOException
	{
		FootfallModel model1 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 1, 1));
		FootfallModel model2 = new FootfallModel(LocalTime.of(6, 5, 30), LocalDate.of(2020, 1, 2));
		List<FootfallModel> listRecords = new ArrayList<FootfallModel>();
		listRecords.add(model1);
		listRecords.add(model2);
		
		DataCalculation.getDailyPeakFootFallsInLastMonth(listRecords, writer);
		verify(writer, times(1)).createWriter(argumentCaptorFilePath.capture());
		verify(writer, times(1)).writeColumnNames(Matchers.<String>anyVararg());
		verify(writer, times(1)).writeEntry(Matchers.<String>anyVararg());
		verify(writer, times(1)).closeOffWriter();
		
		Assert.assertEquals("src/resources/test3.csv", argumentCaptorFilePath.getValue());
	}

}

