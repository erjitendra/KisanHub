import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UrlReader {
	String regionCode[]= {"UK","England","Wales","Scotland"};
	String weatherType[]= {"Tmax","Tmin","Tmean","Sunshine","Rainfall"};
	ArrayList<WeatherModel> weatherModels= new ArrayList<WeatherModel>();
	static BufferedReader in;
	
	public ArrayList<WeatherModel> readData(){
	try {
		
		for(int rc=0;rc<regionCode.length;rc++) {
			for(int wp=0;wp<weatherType.length;wp++) {
				String dynamicUrl="https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/"+weatherType[wp]+"/date/"+regionCode[rc]+".txt";
			
		URL url=new URL(dynamicUrl);
		
		try {
		in = new BufferedReader(new InputStreamReader(url.openStream()));
		ignoreLines();
		
		readingData(rc, wp);
		
	    in.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		}
	} 
	catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return weatherModels;
	
	}
	
	
	private void readingData(int rc, int wp) throws IOException {
		String inputLine;
		while( (inputLine=in.readLine())!=null) {
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
		try {
			in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	}

}
