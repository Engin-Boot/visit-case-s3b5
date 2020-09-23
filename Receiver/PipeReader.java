package Receiver;

import java.util.*;
import java.time.*;

public class PipeReader
{
	/** 
		used to read input from console
	*/
	public static void ConsolePipeReader()
	{
		System.out.println("In pipe");
		try
		{
			Scanner sc= new Scanner(System.in);
			String inputFromPipe;
			// reads line by line
			while ((inputFromPipe = sc.nextLine()) != null)
			{
				AddEventToDs(inputFromPipe);
			}
			sc.close();
		}
		catch (RuntimeException e)
		{
			System.out.println("Error in reading from the pipe" + e);
		}

		Receiver.OperationsThread();
		System.out.println("End of op");
	}

	/** 
		used to add even to the DS maintained
	 	@param inputFromPipe
	*/
	private static void AddEventToDs(String inputFromPipe)
	{
		LocalDateTime date = LocalDateTime.parse(inputFromPipe);
		CountSetters.SetHourCount(date);
		CountSetters.SetDayCount(date);
	}
}
