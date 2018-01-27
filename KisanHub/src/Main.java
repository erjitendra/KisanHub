import java.util.ArrayList;
public class Main 
{
	
	public static void main(String[] args)  
	{
		Reader urlReader=new Reader();
		ArrayList<WeatherModel> weatherModels = urlReader.read();
		Writer output=new Writer();
		output.runFileWriter(weatherModels);
	}
}
