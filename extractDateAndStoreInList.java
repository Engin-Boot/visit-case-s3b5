package Sender;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class extractDateAndStoreInList {
	static boolean dataPopulated;
	static ArrayList<Date> dateList= new ArrayList<>();
	static String filename = extractCSVFile.loadFileName();
	
	public boolean isDataPopulated()
	{
		return extractDateAndStoreInList.dataPopulated;
	}
	
	public static ArrayList<Date> extractDateAndCreateList() throws FileNotFoundException, ParseException
	{
		Scanner sc = new Scanner(new File(filename));
		String rowValue;
		sc.next(); //removing the first useless line
		while(sc.hasNext())
		{
			rowValue = sc.next();
			dateList.add(createDateTimeObject.CreateDateTimeObjectFromData(rowValue));
		}
		dataPopulated =true;
		
		return dateList;
	}
	
	

}
