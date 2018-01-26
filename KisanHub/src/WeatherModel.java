import java.util.ArrayList;

public class WeatherModel 
{
	private String regionName;
	private String weatherParameter;
	private String rowValues[];
	static String New_line_seprater="\n";

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
		String months[]= {"Jan","Feb", "March","Apr","May","June","July","Aug","Sep",
				 "Oct","Nov","Dec","Win"};
		
		String internalRowValues[] = getRowValues();
		
		for(int i=0; i<months.length;i++) 
		{
		String fileRow;
		fileRow= getRegionName()+","+getWeatherParameter()+","+
		internalRowValues[0]+","+months[i]+","+internalRowValues[i+1]+New_line_seprater;
		outputRows.add(fileRow);
		}
		
		return outputRows;
	}
	
}
