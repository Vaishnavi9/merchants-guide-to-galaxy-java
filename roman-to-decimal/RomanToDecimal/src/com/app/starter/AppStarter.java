package com.app.starter;

import com.app.inputOutputProcessor.ProcessInput;
import com.app.inputOutputProcessor.ProcessOutput;



public class AppStarter {
	

	public static void main(String[] args)  
	  { 
		String filePath = null;
		if (args.length != 0)
			filePath = args[0];
		try{
			ProcessInput.readFile(filePath);
			ProcessInput.mapGalacticToIntegerValue();
			ProcessOutput.processAnswerToQuestion();
		}
		catch(Exception e){
			System.out.println("Cannot find the file, please check if it is placed correctly..");
		}
	  }
	
	
}
