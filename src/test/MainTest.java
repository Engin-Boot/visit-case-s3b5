package visitcounter.sender;


import java.io.IOException;
import java.util.MissingResourceException;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

	@Test
	public void whenMainCalledWithValidKeyForPropertiesFileThenCheckifMainExecutedSuccessfully() throws IOException {
		String arg[] = new String[0];
		Main.main(arg);	
		Assert.assertTrue(Main.IsMainExecuting);
	}
	
	
	@Test
	public void whenReadCsvFileNameFromPropertiesFileWithValidKeyThenReturnFileNameWithPath()
	{
		String filePath = Main.getCsvFileName("filename");
		Assert.assertEquals("C:\\Users\\320090269\\eclipse-workspace\\ours\\src\\footfall.csv", filePath);
	}
	
	@Test(expected = MissingResourceException.class)
	public void whenReadCsvFileNameFromPropertiesFileWithInvalidKeyThenThrowException()
	{
		//Main.getCsvFileName("invalidkey");
		Main.getCsvFileName("filename_invalid");
	}
	
	@Test
	public void whenArgValidThenReturnKey()
	{
		String args[] = new String[0];
		String key = Main.checkKey(args);
		Assert.assertEquals("filename", key);
	}
	
	@Test
	public void whenArgInvalidThenReturnInvalidKey()
	{
		String args[] = {"test_invalidfile"};
		String key = Main.checkKey(args);
		Assert.assertEquals("filename_invalid", key);
	}
	
	

}
