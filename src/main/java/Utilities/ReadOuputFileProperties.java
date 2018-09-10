package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadOuputFileProperties 
{
	public Properties OpenOutputFile() throws FileNotFoundException, IOException
	{
		Properties  prop=new Properties();
		prop.load(new FileInputStream(".\\src\\main\\java\\Utilities\\OutPutFile.properties"));
		return prop;
	}
}
