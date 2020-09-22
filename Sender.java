package Sender;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class Sender {
	static boolean isPrinted;
    public static void PrintData(ArrayList<Date> listOfDates)
    {
   	 for(Date d : listOfDates)
   	 {
   		 System.out.println(d);
   	 }
   	 isPrinted = true;
   	 
    }
    public boolean isPrinted()
    {
   	 return Sender.isPrinted;
    }
  
    public static void main(String args[]) throws FileNotFoundException, ParseException
    {
   	 
   	  ArrayList<Date> dateList;
   	 dateList = extractDateAndStoreInList.extractDateAndCreateList();
   	 PrintData(dateList);
   	 
    }

}
