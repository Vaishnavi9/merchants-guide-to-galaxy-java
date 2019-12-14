package main.com.app.starter;

import java.util.List;

import main.com.app.inputProcessor.OutputProcessor;
import main.com.app.inputProcessor.ProcessInput;

public class AppStarter {
	

	public static void main(String[] args) 
	  { 
		List<String> l = ProcessInput.readFileInList("C:\\Project Area\\Merchants-Guide-To-Galaxy-Java\\merchants-guide-to-galaxy-java\\roman-to-decimal\\RomanToDecimal\\input.txt"); 
	    ProcessInput.processTheValuesOfArrays(l);
	    ProcessInput.MapTokentoIntegerValue();
	   OutputProcessor.processReplyForQuestion();
	  }
	
	
}
