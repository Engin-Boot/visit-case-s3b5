package Sender;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createDateTimeObject {
	public static Date CreateDateTimeObjectFromData(String dateTimeString) throws FileNotFoundException, ParseException
	{
		
		Date dateTimeObj;//dateObj,timeObj;
		String dateAndTime;
		SimpleDateFormat formatter=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		formatter.setLenient(false);
		//SimpleDateFormat dateFormat= new SimpleDateFormat("dd/mm/yyyy");
		//SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String[] dateTimeStringSplit = dateTimeString.split(",");
		
		
    	dateAndTime=dateTimeStringSplit[0]+ " "+ dateTimeStringSplit[1];
    	dateTimeObj=formatter.parse(dateAndTime);
		return dateTimeObj;
	}

}
