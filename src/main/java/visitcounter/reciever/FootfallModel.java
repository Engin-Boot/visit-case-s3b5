package visitcounter.reciever;

import java.time.LocalDate;
import java.time.LocalTime;

public class FootfallModel {
    private LocalTime time ;
    private LocalDate date ;

    public FootfallModel( LocalTime time, LocalDate date )
    {
        super();
        this.date = date ;
        this.time = time ;
    }

    public LocalTime getTime()
    {
        return time ;
    }

    public LocalDate getDate()
    {
        return date ;
    }

}
