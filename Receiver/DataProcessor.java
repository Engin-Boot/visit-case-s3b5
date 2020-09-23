package Receiver;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class DataProcessor
{
	/** 
	 Gets the number of Weeks between the 2 dates
	 
	 @param date1
	 @param date2
	*/
	private static int GetNumberOfWeeks(LocalDateTime date1, LocalDateTime date2)
	{
		try
		{
			int numberOfWeeks = (int)ChronoUnit.DAYS.between(date1, date2) / 7 ; 	
			
			return numberOfWeeks;
		}

		catch (java.lang.Exception e)
		{
			System.out.println("Not able to calculate number of weeks ");
			return 0;
		}
	}

	public static double GetAverageForCurrentMonth()
	{
		int currentMonth = LocalDateTime.now().getMonthValue();
		int currentYear = LocalDateTime.now().getYear();

		LocalDateTime currentDate = LocalDateTime.of(currentYear, currentMonth, 01, 0, 0);
		return GetDailyAverage(currentDate);
	}
	/** 
		 This method gets the daily average. By default, it calculates daily average from 1st Jan 2000
	 
	 @return 
	*/
	public static double GetDailyAverage()
	{
		LocalDateTime date = LocalDateTime.of(2000, 01, 01, 0, 0);
		return GetDailyAverage(date);
	}

	/** 
		 This method gets average footfall from given date.
	 
	 @param date Start date for average to be computed
	 @return average
	*/
	public static double GetDailyAverage(LocalDateTime date)
	{
		var obj = 0 ;

		for( Map.Entry<LocalDateTime, Integer> entry : CountSetters.DailyCount.entrySet() ) {
			int compare = date.compareTo(entry.getKey()) ;
			if ( compare > 0 ) {
				obj = entry.getValue() ;
			}
		}

		try
		{
			var avg = obj.Average(a -> a);
			return avg;
		}
		catch (RuntimeException e)
		{
			System.out.println("Error in calculating Daily Average" + e);
			return 0;
		}
	}

	/** 
		 This method gets Hourly average for month
	 
	 @param hour
	 @return 
	*/
	public static double GetHourlyAverage(int hour)
	{
		try
		{
			int count = GetCountOfHour(hour);
			var date1 = CountSetters.DailyCount.ElementAt(0).Key;

			var date2 = CountSetters.DailyCount.ElementAt(CountSetters.DailyCount.size() - 1).Key;
			int numberOfWeeks = GetNumberOfWeeks(date1, date2);
			double avg = (double) count / numberOfWeeks;
			return avg;
		}
		catch (RuntimeException e)
		{
			System.out.println("Error in calculating Hourly Average" + e);
			return 0;
		}
	}

	/** 
		 This method gets the count of given hour
	 
	 @param hour
	 @return 
	*/
	public static int GetCountOfHour(int hour)
	{
		return CountSetters.HourlyCount.containsKey(hour) ? CountSetters.HourlyCount.get(hour) : 0;
	}

	/** 
	 Weekly average
	 
	 @param day
	 @return 
	*/
	public static double GetWeeklyAverage(DayOfWeek day)
	{
		java.lang.Iterable<LocalDateTime, Integer> en = from a in CountSetters.DailyCount where a.Key.DayOfWeek == day select a.Value;

		var totalCount = en.Sum();
		System.Globalization.CultureInfo cul = CultureInfo.CurrentCulture;
		if (CountSetters.DailyCount.isEmpty())
		{
			return 0;
		}

		var latestEntry = CountSetters.DailyCount.ElementAt(CountSetters.DailyCount.size() - 1).Key;
		int weekNum = cul.Calendar.GetWeekOfYear(latestEntry, CalendarWeekRule.FirstDay, DayOfWeek.MONDAY);
		return (double) totalCount / weekNum;
	}
}