package main.com.app.inputProcessor;

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
	//Reading file and putting every line from the file in an list of string at once.
	//required declarations
	static Map<String, String> mapOfGalacticValues=new HashMap<String,String>();
	static Map<String, String> questions = new HashMap<String, String>();  
	static ArrayList<String> metalValues = new ArrayList<String>(); 
	static Map<String, Float> elementValueList = new HashMap<String, Float>();
	static Map<String, Float> tokenIntegerValue = new HashMap<String, Float>();
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
		public static void  processTheValuesOfArrays(List<String> listOfLines) {
			
			for (String line : listOfLines) {
				String arr[] = line.split("((?<=:)|(?=:))|( )");
				if(arr[arr.length-1].endsWith("?")) {
					questions.put(line, "");
					
				}
				else if(arr.length==3 && arr[1].equalsIgnoreCase("is")) {
					mapOfGalacticValues.put(arr[0], arr[2]);
					//System.out.println(mapOfGalacticValues.get(arr[0]));
				}
				else if(line.toLowerCase().endsWith("credits")){
					metalValues.add(line);
				}
				
			}
						
		}
		
		
		public static void MapTokentoIntegerValue(){

			Iterator<?> it = mapOfGalacticValues.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<?, ?> token = (Map.Entry<?, ?>)it.next();
				float integerValue = new RomanToDecimal().romanToDecimal(token.getValue().toString());
				tokenIntegerValue.put(token.getKey().toString(), integerValue);
			}
			mapMissingEntities();
		}
		
		private static void mapMissingEntities(){
			for (int i = 0; i < metalValues.size(); i++) {
				deCodeMissingQuery(metalValues.get(i));
			}
		}



		private static void deCodeMissingQuery(String string) {
			String array[] = string.split("((?<=:)|(?=:))|( )");
			int splitIndex = 0;
			int creditValue = 0; String element= null; String[] valueofElement = null;
			for (int i = 0; i < array.length; i++) {
				if(array[i].toLowerCase().equals("credits")){
					creditValue = Integer.parseInt(array[i-1]);
				}
				if(array[i].toLowerCase().equals("is")){
					splitIndex = i-1;
					element = array[i-1];
				}
				valueofElement = java.util.Arrays.copyOfRange(array, 0, splitIndex);
			}

			StringBuilder stringBuilder = new StringBuilder();
			for (int j = 0; j < valueofElement.length; j++) {
				stringBuilder.append(mapOfGalacticValues.get(valueofElement[j]));
			}
			float valueOfElementInDecimal = new RomanToDecimal().romanToDecimal(stringBuilder.toString());
			elementValueList.put(element, creditValue/valueOfElementInDecimal);
		}

			
		}
		
				
		
		
		
		
		
		
		
		
		
		
		
		



