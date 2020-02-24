package com.app.inputOutputProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProcessInput{

	/**
	 * Below are sample of the kind of values each map would hold.
	 * galacticRomanValueMapping : {pish=X, tegj=L, prok=V, glob=I}
	 * galacticIntegerValue :{pish=10.0, tegj=50.0, prok=5.0, glob=1.0}
	 * questionAndReply :{how much is pish tegj glob glob ?, how many Credits is glob prok Iron ?}
	 * answerToQuestion :[glob glob Silver is 34 Credits, glob prok Gold is 57800 Credits, pish pish Iron is 3910 Credits]
	 * metalValueList :{Gold=14450.0, Iron=195.5, Silver=17.0}	
	 */
	static Map<String, String> galacticRomanValueMapping = new HashMap<String, String>();
	static Map<String, Float> galacticIntegerValue = new HashMap<String, Float>(); //{pish=10.0, tegj=50.0, prok=5.0, glob=1.0}
	static Map<String, String> answerToQuestion = new HashMap<String, String>();  //{how much is pish tegj glob glob ?, how many Credits is glob prok Iron ?}
	static ArrayList<String> metalValues = new ArrayList<String>(); // [glob glob Silver is 34 Credits, glob prok Gold is 57800 Credits, pish pish Iron is 3910 Credits]
	static Map<String, Float> metalValueList = new HashMap<String, Float>(); //{Gold=14450.0, Iron=195.5, Silver=17.0}


	/**
	 * if file path is specified that is picked up else by default Input inside the same package is pickedup.
	 * Each line is picked up and served to processLine() for processing.
	 * @param filePath
	 * @throws IOException
	 */
	public static void readFile(String filePath) throws IOException {
		BufferedReader bufferedReader = null;
		if (filePath == null){
			InputStream in = ProcessInput.class.getResourceAsStream("Input");
			bufferedReader = new BufferedReader(new InputStreamReader(in));
		}
		else{
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
		}
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			processLine(line);
		}
		bufferedReader.close();
	}

	/**
	 * processline adds the input to various maps<K,T> based on different conditions.
	 * @param line
	 */
	public static void processLine(String line){
		String arr[] = line.split("((?<=:)|(?=:))|( )");

		if (line.endsWith("?")){
			answerToQuestion.put(line,"");
		}
		else if (arr.length == 3 && arr[1].equalsIgnoreCase("is")){
			galacticRomanValueMapping.put(arr[0], arr[arr.length-1]);	
		}
		else if(line.toLowerCase().endsWith("credits")){
			metalValues.add(line);
		}
	}

	/**
	 * Maps tokens to Roman equivalent  
	 * {pish=X, tegj=L, prok=V, glob=I}
	 */
	public static void mapGalacticToIntegerValue(){

		Iterator<?> it = galacticRomanValueMapping.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> token = (Map.Entry<?, ?>)it.next();
			float integerValue = new RomanToDecimal().romanToDecimal(token.getValue().toString());
			galacticIntegerValue.put(token.getKey().toString(), integerValue);
		}
		mapMissingEntities();
	}

	/**
	 * FInds the value of elements by decoding queries like [glob glob Silver is 34 Credits]
	 */
	private static void mapMissingEntities(){
		for (int i = 0; i < metalValues.size(); i++) {
			calculateMetalValues(metalValues.get(i));
		}
	}
	
	/**
	 * Calculates the values of various elements and appends the same to map elementValueList.
	 * elementValueList :{Gold=14450.0, Iron=195.5, Silver=17.0}
	 * @param query
	 */
	private static void calculateMetalValues(String query){
		String array[] = query.split("((?<=:)|(?=:))|( )");
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
			stringBuilder.append(galacticRomanValueMapping.get(valueofElement[j]));
		}
		float valueOfElementInDecimal = new RomanToDecimal().romanToDecimal(stringBuilder.toString());
		metalValueList.put(element, creditValue/valueOfElementInDecimal);
	}

}
