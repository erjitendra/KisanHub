import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer 
{
	static FileWriter fileWriter;
	static String header="region_code,weather_param,year, key, value";
	static String new_line_seprater="\n";
	
	public void runFileWriter(ArrayList<WeatherModel> weatherModels) 
	{
		fileCreate();
		writeDataInFile(weatherModels);
		endInWriting(); 
	}
	
	public void fileCreate() 
	{
		try 
		{
			fileWriter=new FileWriter("weather.csv");
			fileWriter.append(header);
			fileWriter.append(new_line_seprater);
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeDataInFile(ArrayList<WeatherModel> weatherModels) 
	{
	
		try 
		{
			for(int i=0;i<weatherModels.size();i++) 
			{
			WeatherModel wm= weatherModels.get(i);
			ArrayList<String> outputRows =wm.outputRows();
			for(int j=0;j<outputRows.size();j++) 
			{
			fileWriter.append(outputRows.get(j));
			}
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void endInWriting() 
	{
		try 
		{
			fileWriter.flush();
			fileWriter.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}
}
