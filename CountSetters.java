package Receiver;

import java.util.*;
import java.time.*;

public class CountSetters
{
	//Daily count (Date-count)
	public static HashMap<LocalDateTime, Integer> DailyCount = new HashMap<LocalDateTime, Integer>();

	//Hourly count (Hour - count)
	public static HashMap<Integer, Integer> HourlyCount = new HashMap<Integer, Integer>();

	/** 
		 This method will update counter value in DailyCount
	*/
	public static void SetDayCount(LocalDateTime date)
	{
		LocalDateTime tempDate = date; // changed
		if (DailyCount.containsKey(tempDate))
		{
			DailyCount.put(tempDate, DailyCount.get(tempDate) + 1) ;

		}
		else
		{
			DailyCount.put(tempDate, 1);
		}
	}

	/** 
		This method will update HourCounter when counter object arrives
	*/
	public static void SetHourCount(LocalDateTime time)
	{
		int hour = time.getHour() ;
		// int hour = time.TimeOfDay.Hours;
		if (HourlyCount.containsKey(hour))
		{
			HourlyCount.put(hour, HourlyCount.get(hour) + 1) ;
			// HourlyCount.get(hour)++;
		}
		else
		{
			HourlyCount.put(hour, 1);
		}
	}
}