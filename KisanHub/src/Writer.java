import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
	static FileWriter fileWriter;
	static String Header="region_code,weather_param,year, key, value";
	static String New_line_seprater="\n";
	
	public void runFileWriter(ArrayList<WeatherModel> weatherModels) 
	{
		fileCreate();
		writeDataInFile(weatherModels);
		endInWriting(); 
		
	}
	
	public void fileCreate() 
	{
		try {
			fileWriter=new FileWriter("weather.csv");
			fileWriter.append(Header);
			fileWriter.append(New_line_seprater);
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
public static void writeDataInFile(ArrayList<WeatherModel> weatherModels) 
{
	
		try {

			for(int i=0;i<weatherModels.size();i++) 
			{
				
				WeatherModel wm= weatherModels.get(i);
				ArrayList<String> outputRows =wm.outputRows();
				
				for(int j=0;j<outputRows.size();j++) 
				{
					fileWriter.append(outputRows.get(j));
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public void endInWriting() 
{
	try {
		fileWriter.flush();
		fileWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
