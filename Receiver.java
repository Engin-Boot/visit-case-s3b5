package Receiver;

import java.time.*;

public class Receiver
{
	private static String OutputToFile;
	private static String getOutputToFile()
	{
		return OutputToFile;
	}
	private static void setOutputToFile(String value)
	{
		OutputToFile = value;
	}
	/** 
		 Main
	*/
	public static void main(String[] args)
	{
		PipeReader.ConsolePipeReader();
	}

	/** 
		 Main thread
	*/
	public static void OperationsThread()
	{
		System.out.println("Reading from pipe Finished");
		System.out.println("Calculating Daily average for the month");
		double dailyAverage = DataProcessor.GetAverageForCurrentMonth();
		setOutputToFile("Daily average for current month = " + dailyAverage + "\n");

		for (int i = 0; i <= 24; i++)
		{
			double hourlyAverage = DataProcessor.GetHourlyAverage(i);
			setOutputToFile(getOutputToFile() + "Average footfall at " + i + " hours is = " + hourlyAverage + "\n");
		}
		System.out.println("Calculating average for a Sunday");
		double averageForSunday = DataProcessor.GetWeeklyAverage(DayOfWeek.SUNDAY);
		System.out.println("Average footfall on sunday is = " + averageForSunday);
		setOutputToFile(getOutputToFile() + "Average footfall on Sunday = " + averageForSunday);
		DataToCsv.WriteToCsv(getOutputToFile());
	}
}