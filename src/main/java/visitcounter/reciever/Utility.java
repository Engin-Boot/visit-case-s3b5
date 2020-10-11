package visitcounter.reciever;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Utility {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("outputpath") ;

    public static String[] CommaSplitData( String entry )
    {
        String splitEntry[] = entry.split(",") ;
        return splitEntry ;
    }

    public static LocalDate stringToDate( String dateString )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd") ;
        LocalDate date = LocalDate.parse(dateString, formatter) ;
        return date ;
    }

    public static LocalTime stringToTime( String timeString )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss") ;
        LocalTime time = LocalTime.parse(timeString, formatter) ;
        return time ;
    }

    public static String getFilePathOfCsvOutput( String keyString )
    {
        String filePath = resourceBundle.getString(keyString) ;
        return filePath ;
    }

    public static int[] getWorkingHoursForOneDay() 
    {
        int startingHour = 9, endingHour = 17 ;
        int[] workingHours = new int[2] ;
        workingHours[0] = startingHour ;
        workingHours[1] = endingHour ;
        return workingHours ;
    }




}
