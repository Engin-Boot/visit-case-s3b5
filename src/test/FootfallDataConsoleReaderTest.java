package visitcounter.receiver;

//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

//import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class FootfallDataConsoleReaderTest {

	@Mock
	BufferedReader br;
	
	@Mock
	StoreFootfallEntry storer;
	
	ArgumentCaptor<String> argumentCaptorRecord = ArgumentCaptor.forClass(String.class);
	
	@Test
	public void whenReadDataRecordFromConsoleThenThrowNoException() throws Exception
	{
		FootfallReader consoleReader = new FootfallReader();
		when(br.readLine()).thenReturn("record1", "record2", null);
		doNothing().when(storer).storeAsObject(any());
		
		consoleReader.readFootfallData(storer, br);
		
		verify(storer, times(2)).storeAsObject(argumentCaptorRecord.capture());
		Assert.assertEquals("record1", argumentCaptorRecord.getAllValues().get(0));
		Assert.assertEquals("record2", argumentCaptorRecord.getAllValues().get(1));
		
	}
	
	@Test(expected = IOException.class)
	public void whenReadDataRecordFromConsoleButBufferedReaderReadLineDoesntWorkThenThrowException() throws Exception
	{
		FootfallReader consoleReader = new FootfallReader();
		doThrow(new IOException()).when(br).readLine();
		doNothing().when(storer).storeAsObject(any());
		
		consoleReader.readFootfallData(storer, br);	
	}
	
	@Test(expected = Exception.class)
	public void whenReadDataRecordWithInvalidFormatFromThenThrowException() throws Exception
	{
		FootfallReader consoleReader = new FootfallReader();
		when(br.readLine()).thenReturn("record1", "record2", null);
		doThrow(new IOException()).when(storer).storeAsObject(any());
		
		consoleReader.readFootfallData(storer, br);	
	}

}

