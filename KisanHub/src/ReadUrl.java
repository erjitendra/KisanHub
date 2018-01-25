import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class ReadUrl {
	
	static FileWriter fileWriter;
	static String Header="region_code,weather_param,year, key, value";
	static String New_line_seprater="\n";
	static WeatherModel urlWeatherModel=new WeatherModel();
	static BufferedReader in;
	
	public static void main(String[] args) throws IOException {
		
		fileWriter=new FileWriter("weather.csv");
		fileWriter.append(Header);
		fileWriter.append(New_line_seprater);
		
		String regionCode[]= {"UK","England","Wales","Scotland"};
		String weatherType[]= {"Tmax","Tmin","Tmean","Sunshine","Rainfall"};
		try {
			for(int rc=0;rc<regionCode.length;rc++) {
				for(int wp=0;wp<weatherType.length;wp++) {
					String dynamicUrl="https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/"+weatherType[wp]+"/date/"+regionCode[rc]+".txt";
				
			URL url=new URL(dynamicUrl);
			 in = new BufferedReader(
		    new InputStreamReader(url.openStream()));
			
			ignoreLines();
			
			
			ArrayList<WeatherModel> weatherModels= new ArrayList<WeatherModel>();
			
			
			 
			String inputLine;
		  while( (inputLine=in.readLine())!=null) {
			  
			  
			  WeatherModel wm=new WeatherModel();
			  
			  
			  String valueTypes[]=inputLine.split(" +");
			 
			  wm.setRegionName(regionCode[rc]); 
			  wm.setWeatherParameter(weatherType[wp]);
			 wm.setRowValues(valueTypes);
			 
			 
			
			 weatherModels.add(wm);
			 
			 
		  }
		  
		  generateOutputFile(weatherModels);
			
		    in.close();
			}
			}
			fileWriter.flush();
			fileWriter.close();
		} 
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	}
	
	public static void ignoreLines() 
	{
	for(int i=0;i<8;i++) 
	{
		try {
			in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	}
	
	public static void generateOutputFile(ArrayList<WeatherModel> weatherModels) {

		
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

}



