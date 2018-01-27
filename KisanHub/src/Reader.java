import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Reader 
{
	private String regionCode[]= {"UK","England","Wales","Scotland"};
	private String weatherParameters[]= {"Tmax","Tmin","Tmean","Sunshine","Rainfall"};
	private HashMap<String,String> parameterMap = weatherParameterMap();
	private ArrayList<WeatherModel> weatherModels= new ArrayList<WeatherModel>();
	private URL url;
	private BufferedReader bufferedReader;
	
	public ArrayList<WeatherModel> read()
	{
	
		try 
		{
			for(int rc=0;rc<regionCode.length;rc++) 	
			{
				for(int wp=0;wp<weatherParameters.length;wp++) 	
				{
					String dynamicUrl="https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/"+weatherParameters[wp]+"/date/"+regionCode[rc]+".txt";
					url=new URL(dynamicUrl);
					
					try 
					{
						bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
						ignoreLines();
						readingData(rc, wp);
						bufferedReader.close();
					}
					catch (UnknownHostException e) {
						System.out.println("Wrong Url Provided"+url);
					}
					
					catch (FileNotFoundException e) {
						System.out.println("File Not Found on HostWebsite"+url);
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
			System.out.println("The url is not well formed:   " + url);	
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
			wm.setWeatherParameter(parameterMap.get(weatherParameters[wp]));
			wm.setRowValues(valueTypes);
			weatherModels.add(wm);
		 }
	}
	
	
	private void ignoreLines() 
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
	
	public HashMap<String,String> weatherParameterMap()
	{
		HashMap<String,String> hashmap=new HashMap<String,String>();
		hashmap.put("Tmax", "Max Temp");
		hashmap.put("Tmin", "Min Temp");
		hashmap.put("Tmean", "Mean Temp");
		hashmap.put("Sunshine", "Sunshine");
		hashmap.put("Rainfall", "Rainfall");
		return hashmap;
	}

}
