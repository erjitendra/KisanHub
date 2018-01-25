import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args)  
	{
		UrlReader urlReader=new UrlReader();
		ArrayList<WeatherModel> weatherModels = urlReader.readData();
		
		Output output=new Output();
		output.runFileWriter(weatherModels);
	
	}

}
