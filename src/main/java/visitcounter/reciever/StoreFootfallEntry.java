package visitcounter.reciever;

import java.util.ArrayList;
import java.util.List;

public class StoreFootfallEntry {
    public static List<FootfallModel> footfallEntries = new ArrayList<FootfallModel>() ;

    public List<FootfallModel> getFootfallEntries()
    {
        return footfallEntries ;
    }

    public void storeAsObject( String entry )
    {
        String[] dateTimeArrayStrings = Utility.CommaSplitData(entry) ;
        footfallEntries.add( new FootfallModel( Utility.stringToTime(dateTimeArrayStrings[1]),
                Utility.stringToDate(dateTimeArrayStrings[0])));

        
    }

}
