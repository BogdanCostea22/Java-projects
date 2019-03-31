package tema5.tema5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

	//Functie de creeare a print writerului
	public static PrintWriter createWriter(String problem)
	{
		try {
			return new PrintWriter(problem);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main( String[] args ) 
    {
	//Preluarea datelor din fisier	
	List<MonitoredData> list=null;
		try {
			list=Files.lines(Paths.get("Activity.txt"))
					.map(element->{return new MonitoredData(element);})
					.collect(Collectors.toList());
			
			/*for(MonitoredData mo:list)
			{
				System.out.println(mo.toString());
			}*/
		   
		} catch (Exception e) {
		    e.printStackTrace();
		}			
    	
		//Problema1 determinarea numarului de elemente dinstincte din lista
		
		List<String> string=list.stream().map(element->element.takeDay()).distinct().collect(Collectors.toList());
		 long l=list.stream().map(element->element.takeDay()).distinct().count();
		 System.out.println(l+"      "+string.size());
		 
		 
		 //Problema 2 --pentru fiecare activitate sa se determine numarul de evenimente
		 Map<String,Integer> map2=list.stream()
				 				.collect(Collectors.groupingBy(s->s.getActivity(),Collectors.summingInt(adderElement->1)));
		 
		 //Afisarea rezultatelor in fisier
		 PrintWriter writerP2=createWriter("Problema2");
    	 for(String string1:map2.keySet())
   		 writerP2.println("string "+string1+"Valuea"+map2.get(string));
    	 
    	 writerP2.close();
    	 
    	 //Problema 3 generates a structure
    	 Map<Integer,Map<String,Integer>> map3=list
    			 .stream()
    			 .collect(Collectors.groupingBy(
    					 day->
    					 {
    						 Integer integer=Integer.valueOf(day.getStartTime().getDate());
    						// System.out.println("Aici ii ziua:"+day.getStartTime().getDate());
    						 
    						 return integer;
    					}
    					,Collectors.groupingBy(activity->activity.getActivity()
    					,Collectors.summingInt(adderElement->1))));
    	
    	 //Printarea rezultatelor in fisier
    	PrintWriter writeP3=createWriter("Problema3");
    	
    	 for(Integer day:map3.keySet())
    	{
    		writeP3.println("Day"+day.intValue()+":");
    		Map<String,Integer> map3Print=map3.get(day);
    		for(String activity:map3Print.keySet())	
    			writeP3.println("Activity "+activity+" have :"+map3Print.get(activity));
    	}
    	 
    	 writeP3.close();
    	
    	//Problema 4 
    	 

    	Map<String,Long> map4=list.stream().collect(Collectors.groupingBy(activity->activity.getActivity(),Collectors.summingLong(element->element.difference())));
        Map<String, Long> map4Result = map4.entrySet().stream()
                .filter(map -> map.getValue() >36000*1000)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        //List<String> result=map4.keySet().stream().filter(element->map4.get(element)>36000*1000).collect(Collectors.toList());
        //System.out.println(map4Result);
        //System.out.println(map4);
        //Afisarea rezultatelor in fisier
       
       PrintWriter writeP4=createWriter("Problema4");
        
        for(String activity:map4Result.keySet())
    		writeP4.println(activity);
        
        writeP4.close();
    	
    	//Problema 5
    	Map<String,Integer> findActivityWithDuration=list.stream()
    													 .filter(duration->duration.difference()<5*3600*1000)
    													 .collect(Collectors.groupingBy(activity->activity.getActivity()
    															 ,Collectors.summingInt(adderElement->1)));
    	/*System.out.println(map2);
    	System.out.println(findActivityWithDuration);*/
    	
    	List<String> map5=new ArrayList<String>();
    						  findActivityWithDuration.keySet()
    						  .stream()
    						  .filter(element->(map2.get(element)*90)/100<=findActivityWithDuration.get(element))
    						  //.collect(Collectors.toList());
    						  .forEach(a->{
    							  String activity=a.toString();
    							  map5.add(activity);
    						  });
    
			PrintWriter write=createWriter("Problema5");
			
			//Printam rezultatul obtinut
			for(String string2:map5)
				write.println(string2);
			write.close();
    	
    }
}
