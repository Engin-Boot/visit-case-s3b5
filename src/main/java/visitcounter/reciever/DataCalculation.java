package visitcounter.reciever;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataCalculation {

    private static Map<String, Integer> getFootfallCountEveryHour(List<FootfallModel> footFallEntries) {
        Map<String, Integer> footfallCountEveryHour = new LinkedHashMap<>();
        int[] workingHour = Utility.getWorkingHoursForOneDay();
        int openTime = workingHour[0];
        int closingTime = workingHour[1];

        for (int i = openTime; i <= closingTime; i++) {
            String hourFootFallCount = String.valueOf(i);
            footfallCountEveryHour.put(hourFootFallCount, 0);
        }

        for (FootfallModel entry : footFallEntries) {   
            String exactTime = String.valueOf(entry.getTime().getHour());
            footfallCountEveryHour.merge(exactTime, 1, Integer::sum);
        }
        return footfallCountEveryHour;
    }

    public static void avgFootfallsPerHourInADay(List<FootfallModel> footFallEntries, IFootFallCsvWriter iFootFallCsvWriter)
            throws IOException
    {
        Map<String, Integer> everyHourFootFallCount = getFootfallCountEveryHour(footFallEntries) ;
        iFootFallCsvWriter.createWriter(Utility.getFilePathOfCsvOutput("filename1")); 

        String[] columnNames = { "Working Hours", "Avg Foot Fall An Hour" } ;

        iFootFallCsvWriter.writeColumnNames(columnNames);

        everyHourFootFallCount.forEach(( key, value ) -> {
            Double dailyCountPerHour = value.doubleValue() / 30 ;
            String[] entry = { key, dailyCountPerHour.toString() } ;
            iFootFallCsvWriter.writeEntry(entry);
        }); 
        iFootFallCsvWriter.closeOffWriter();
    }

    private static Map<String,Integer> occurencesOfDaysInAWeek(List<FootfallModel> footFallRecords){
		Map<String,Integer> occurencesOfDaysInAWeek = new LinkedHashMap<>();
		String date = ""; 
		for(FootfallModel entry: footFallRecords) {
			if(!date.equals(entry.getDate().toString())) {
                String exactTime = entry.getDate().getDayOfWeek().toString() ;
				occurencesOfDaysInAWeek.merge(exactTime, 1, Integer::sum);
				date = entry.getDate().toString();
			}	
		}
		return occurencesOfDaysInAWeek;
		
	} 
    
    private static Map<String,Integer> footFallsPerDayInAWeek(List<FootfallModel> footFallRecords){
		Map<String,Integer> footFallInADay = new LinkedHashMap<>();
		for(FootfallModel record: footFallRecords) {
			footFallInADay.merge(record.getDate().getDayOfWeek().toString(), 1, Integer::sum);
		}
		return footFallInADay;
	}
    
    public static void dailyFootfallInAWeek( List<FootfallModel> footFallEntries, IFootFallCsvWriter iFootFallCsvWriter )
            throws IOException
    {
        Map<String, Integer> countPerDayInAWeek = footFallsPerDayInAWeek(footFallEntries) ;
        Map<String, Integer> occurencesOfDaysInAWeek = occurencesOfDaysInAWeek(footFallEntries) ;

        iFootFallCsvWriter.createWriter(Utility.getFilePathOfCsvOutput("filename2"));

        String[] ColumnNames = { "Day of the week", "Daily foot falls in a week" } ;

        iFootFallCsvWriter.writeColumnNames(ColumnNames);

        countPerDayInAWeek.forEach(( key, value ) -> {
            Double dailyFootFallPerDayInAWeek = value.doubleValue() / occurencesOfDaysInAWeek.get(key) ;
            String[] entry = { key.toString(), dailyFootFallPerDayInAWeek.toString() } ;
            iFootFallCsvWriter.writeEntry(entry);
        });

        iFootFallCsvWriter.closeOffWriter();
    } 

    private static String[] getPeakData(Map.Entry<LocalDate,Integer> entry, int year, String peakDate, Integer peakValue) {
		String[] data = {peakDate, peakValue.toString()};
		if(entry.getKey().getYear() == year && entry.getValue() > peakValue) {
				data[0] = entry.getKey().toString();
				data[1] = entry.getValue().toString();
			}
		return data;
	}

    private static Map<LocalDate,Integer> footFallCountEveryDate(List<FootfallModel> footFallEntries){
		Map<LocalDate,Integer> countFootFallForEveryDate = new LinkedHashMap<>();
		for(FootfallModel record: footFallEntries) {
			countFootFallForEveryDate.merge(record.getDate(), 1, Integer::sum);
		}
		return countFootFallForEveryDate;
	}


    public static void dailyPeakFootfallsInAMonth( int month, int year, List<FootfallModel> footFallEntries, IFootFallCsvWriter iFootFallCsvWriter )
            throws IOException
    {
        Map<LocalDate, Integer> footFallCountEveryDate = footFallCountEveryDate(footFallEntries) ;
        
        iFootFallCsvWriter.createWriter(Utility.getFilePathOfCsvOutput("filename3"));

        String [] ColumnNames = {"Date", "Peak FootFall for current month"};

        iFootFallCsvWriter.writeColumnNames(ColumnNames);

        Integer peakValue = 0 ;
        String peakDate = "2020/07/10" ;
        String [] data = new String[2];

        for (Map.Entry<LocalDate,Integer> entry : footFallCountEveryDate.entrySet()) {
			if(entry.getKey().getMonthValue() == month)
			{
				data = getPeakData(entry, year, peakDate, peakValue);
				peakDate = data[0];
				peakValue = Integer.parseInt(data[1]);
				
			}
		}
		iFootFallCsvWriter.writeEntry(data);
		iFootFallCsvWriter.closeOffWriter();	
        
    }

    private static int[] getLastMonthWithYear(List<FootfallModel> footFallEntries) {
		FootfallModel previousEntry = footFallEntries.get(footFallEntries.size()-1);
		int lastMonthWithYear[] = new int[2];
		lastMonthWithYear[0] = previousEntry.getDate().getMonthValue();
		lastMonthWithYear[1] = previousEntry.getDate().getYear();
		
		return lastMonthWithYear;
	}

    public static void getDailyPeakFootFallsInLastMonth(List<FootfallModel> footFallRecords, IFootFallCsvWriter iFootFallCsvWriter) throws IOException {
		int[] lastMonthWithYear = getLastMonthWithYear(footFallRecords);
		dailyPeakFootfallsInAMonth(lastMonthWithYear[0], lastMonthWithYear[1], footFallRecords, iFootFallCsvWriter);
		
	}

}

