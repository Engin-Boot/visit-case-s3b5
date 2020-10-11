package visitcounter.sender;

import java.util.List;

public class SimulatorLogic {
    IFootFallWriter writer;

    public SimulatorLogic(IFootFallWriter writer) {
        super();
        this.writer = writer;
    }

    public void sensor(int dataCount) throws InterruptedException {
        if (dataCount % 10 == 0)
            Thread.sleep(5);
    }

    public void sendFootFallData(List<String> footfallEntries) {
        int dataSent = 0;
        for (String entry : footfallEntries) {
            writer.writeFootFallEntry(entry);
            dataSent++;
            try {
                sensor(dataSent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    
    }
}
