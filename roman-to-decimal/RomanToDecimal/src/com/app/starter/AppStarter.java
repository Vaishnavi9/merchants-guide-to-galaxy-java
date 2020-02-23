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
			ProcessInput.ProcessFile(filePath);
			ProcessInput.MapTokentoIntegerValue();
			ProcessOutput.processReplyForQuestion();
		}
		catch(Exception e){
			System.out.println("Oops !! File Not Found ");
		}
	  }
	
	
}
