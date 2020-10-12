package visitcounter.sender;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimulatorLogicTest {

	@Mock
	FootFallWriter consoleWriter;
	
	ArgumentCaptor<String> argumentCaptorListRecords = ArgumentCaptor.forClass(String.class);
	
	@Test
	public void whenFootFallDataSentInBunchThenNoException() throws InterruptedException
	{
		List<String> footFallRecords = new ArrayList<String>();
		footFallRecords.add("testrecord1");
		footFallRecords.add("testrecord2");
		footFallRecords.add("testrecord3");
		footFallRecords.add("testrecord4");
		footFallRecords.add("testrecord5");
		footFallRecords.add("testrecord6");
		footFallRecords.add("testrecord7");
		footFallRecords.add("testrecord8");
		footFallRecords.add("testrecord9");
		footFallRecords.add("testrecord10");
		SimulatorLogic consoleWriterSimulatorLogic = new SimulatorLogic(consoleWriter);
		consoleWriterSimulatorLogic.sendFootFallData(footFallRecords);
		
		verify(consoleWriter, times(footFallRecords.size())).writeFootfallEntry(argumentCaptorListRecords.capture());
		
		List<String> capturedRecords = argumentCaptorListRecords.getAllValues();

		int index = 0;
		for(String footfallRecord: footFallRecords) {
			assertEquals("Asserting record",footfallRecord, capturedRecords.get(index++));
		}
	}

}

