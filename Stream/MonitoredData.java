package tema5.tema5;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class MonitoredData {
	private Date startTime, endTime;
	private String activity;
	
	//Constructor 
	public MonitoredData(String data) {
		//Preluarea datelor din sirul de caractere
		String[] info=data.split("		");
		String start=info[0];
		String end=info[1];
		String activity2=info[2];
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			startTime= format.parse(start.trim());
			endTime=format.parse(end.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.activity=activity2.trim();
	}
	
	//Gettere si settere
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	//ToString
	public String toString()
	{
		return startTime+" "+endTime+" "+activity;
	}
	
	//Public make difference between two times
	public long difference()
	{
		long difference=endTime.getTime()-startTime.getTime();
		return difference;
	}
	//Distinct by date
	public String takeDay()
	{
		return startTime.getDay()+endTime.getDay()+startTime.getMonth()+endTime.getYear()+startTime.getYear()+endTime.getYear()+"";
	}
	//Equals
	public boolean equals(Object obj)
	{
		  if (obj == null) return false;
		  if (obj == this) return true;
		MonitoredData data=(MonitoredData)obj;
		if( data.getStartTime().getDay()!=startTime.getDay())return false;
		else
		{if(data.getStartTime().getMonth()!=startTime.getMonth())return false;
		 if(data.getStartTime().getYear()!=startTime.getYear())return false;
		 else return true;
		}
	}	
}
