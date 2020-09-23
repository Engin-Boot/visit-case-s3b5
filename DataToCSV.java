package Receiver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class DataToCSV {
	private static String GetFilePath()
	{
		final String fileName = "Output.csv";
		String filePath = System.getProperty("user.dir") + fileName; // current directory
		// String filePath = AppDomain.CurrentDomain.BaseDirectory + fileName;
		return filePath;
	}
	public static int WriteToCsv(String data) throws IOException
	{
		String filePath = GetFilePath();
		FileOutputStream file = new FileOutputStream(filePath);
		//OutputStreamWriter output = new OutputStreamWriter(file);
		//OutputStreamWriter sw = new OutputStreamWriter(filePath)

		try
		{
			try (OutputStreamWriter sw = new OutputStreamWriter(file))
			{
				sw.write(data + System.lineSeparator());
				sw.close();
				return 1;
			}
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return 0;
		}
	}

}
