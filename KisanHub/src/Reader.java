import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Reader 
{
	String regionCode[]= {"UK","England","Wales","Scotland"};
	String weatherType[]= {"Tmax","Tmin","Tmean","Sunshine","Rainfall"};
	ArrayList<WeatherModel> weatherModels= new ArrayList<WeatherModel>();
	static BufferedReader bufferedReader;
	
	public ArrayList<WeatherModel> read()
	{
	
		try 
		{
			
			for(int rc=0;rc<regionCode.length;rc++) 
			{
			for(int wp=0;wp<weatherType.length;wp++) 
			{
			String dynamicUrl="https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/"+weatherType[wp]+"/date/"+regionCode[rc]+".txt";
			URL url=new URL(dynamicUrl);
			
			try 
			{
				bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
				ignoreLines();
				readingData(rc, wp);
				bufferedReader.close();
			}
			
			catch (IOException e) 
			{
			   e.printStackTrace();
			}
			
			}
			}
		}
		
		catch (MalformedURLException e) 
		{
			
			e.printStackTrace();
		}
		
		return weatherModels;
	
	}
	
	
	private void readingData(int rc, int wp) throws IOException 
	{
		String inputLine;
		while( (inputLine=bufferedReader.readLine())!=null) 
		{
			WeatherModel wm=new WeatherModel();
			String valueTypes[]=inputLine.split(" +");
			wm.setRegionName(regionCode[rc]); 
			wm.setWeatherParameter(weatherType[wp]);
			wm.setRowValues(valueTypes);
			weatherModels.add(wm);
		 }
	}
	
	
	public static void ignoreLines() 
	{
		  for(int i=0;i<8;i++) 
		  {
		  try 
		  {
			bufferedReader.readLine();
		  } 
		  catch (IOException e) 
		  {
				e.printStackTrace();
		  }	
		  }	
	}

}
