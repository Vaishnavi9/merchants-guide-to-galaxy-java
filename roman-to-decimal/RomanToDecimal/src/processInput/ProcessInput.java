package processInput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessInput {
	
	
	//Main list to be processed..
	static List<String[]> splitInputValues=new ArrayList<String[]>();
	//mapping romans to galactic values
	static Map<String, String> mapOfGalacticValues=new HashMap<String,String>();
	//conatins only the expression string array
	static List<String[]> expressions=new ArrayList<String[]>(); 
	
		
		public static void main(String[] args) 
		  { 
		    List l = readFileInList("C:\\Project Area\\Merchants-Guide-To-Galaxy-Java\\merchants-guide-to-galaxy-java\\roman-to-decimal\\RomanToDecimal\\input.txt"); 
		    
		    splitInputValues=processTheValuesOfArrays(l);
		    
		    mapOfGalacticValues=galacticValuesToRomans(splitInputValues);
		    
		    expressions=findExpressions(splitInputValues);
		    
		    /*Iterator<String> itr = l.iterator();
		    while (itr.hasNext()) 
		      System.out.println(itr.next());*/
		    
		    System.out.println(expressions.size());
		  } 
	
		
		//Reading file and putting every line from the file in an list of string at once.
		public static List<String> readFileInList(String fileName) 
		  { 
		  List<String> lines = Collections.emptyList(); 
		  try
		   { 
		      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
		   } 
		catch (IOException e) 
		    { 
		      System.out.println("An exception raised "+e); 
		    } 
		    return lines; 
		  } 
			
		
		
		//processing the array list by reading the file 
		public static List<String[]>  processTheValuesOfArrays(List<String> listOfLines) {
			
			List<String[]> tempSplit=new ArrayList<String[]>();
			
			//converting listOfLines into an arrays of strings  
			for (int i = 0; i < listOfLines.size(); i++) {
				tempSplit.add(listOfLines.get(i).split(" "));
				}
			
			return tempSplit;
			
		}
		
		
		
		
		//map the galatic values to romans by putting it in the map
		public static Map<String, String> galacticValuesToRomans(List<String[]> processList){
			Map<String, String> galacticToRoman=new HashMap<String, String>();
			for (String[] strings : processList) {
				if(strings.length==3 && strings[2].matches("I||V||X||C||L||M")) {
						galacticToRoman.put(strings[0], strings[2]);
				}
			}
			return galacticToRoman;
		}
		
		
		
		
		//Find the value of iron, silver, gold
		public static List<String[]> findExpressions(List<String[]> processValues) {
			List<String[]> expressions=new ArrayList<String[]>();
			//filtering only those lines that would form expressions
			for (String[] strings : processValues) {	
				for(int i=0;i<strings.length;i++) {
				//check if the line contains number and 
					if(strings[strings.length-1].contains("Credits")
					&& strings[i].matches(".*\\d.*")) {
						expressions.add(strings);	
						//System.out.println(strings[i]);
					}
				}
			}
			return expressions;
		} 
		

		
		


}
