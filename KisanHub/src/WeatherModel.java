import java.util.ArrayList;

public class WeatherModel 
{
	private String regionName;
	private String weatherParameter;
	private String rowValues[];
	private String new_line_seprater="\n";

	public String[] getRowValues() {
		return rowValues;
	}
	public void setRowValues(String[] rowValues) {
		this.rowValues = rowValues;
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getWeatherParameter() {
		return weatherParameter;
	}
	public void setWeatherParameter(String weatherParameter) {
		this.weatherParameter = weatherParameter;
	}
	
	public ArrayList<String> outputRows() 
	{
		ArrayList<String> outputRows=new ArrayList<String>();
		String months[]= {"JAN","FEB", "MAR","APR","MAY","JUN","JUL","AUG","SEP",
				 "OCT","NOV","DEC","WIN","SPR","SUM","AUT","ANN"};
		
		String internalRowValues[] = getRowValues();
		
		for(int i=0; i<months.length;i++) 
		{
			String fileRow;
			String weatherValue=internalRowValues[i+1];
			if(weatherValue.matches("---")) 
			{
				weatherValue="N/A";
			}
			fileRow= getRegionName()+","+getWeatherParameter()+","+
			internalRowValues[0]+","+months[i]+","+weatherValue+new_line_seprater;
			outputRows.add(fileRow);
		}
		
		return outputRows;
	}
	
}
