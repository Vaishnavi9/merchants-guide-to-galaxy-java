package romanToDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RomanToDecimal {
	
	
	

    public static Boolean filterInvalids(String num) {
    	Boolean flag=true;
    	

//    	List<String> sequence=new ArrayList<String>();
    	
    	String[] sequence= {"III","XXX","CCC","MMM"};
    	
    	
    	for (String string : sequence) {
			if(num.contains(string)) {
				int pos=num.indexOf(string)+3;
				
				
				char romanArray[]=num.toUpperCase().toCharArray();
				
				
				
				
				System.out.println(romanArray[pos]);
				
				flag=false;
			}
		}
    	
    	
    	
    	return flag;
    	
    }

    public static void main(String[] args) {
        HashMap<Character,Integer> romanMap=new HashMap<Character,Integer>();


        String romanNum="xxxcx";
        
        
        System.out.println(RomanToDecimal.filterInvalids(romanNum.toUpperCase()));
        

        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        char romanArray[]=romanNum.toUpperCase().toCharArray();

        
        List<String> invalids=new ArrayList<String>();
        invalids.add("IIII"); 
        
        
        
        
        
        
        
        int decimalEq=romanMap.get(romanArray[0]);
        int temp=romanMap.get(romanArray[0]);

        
        
        
        
        
        //Actual Conversion
        for (int i = 1; i < romanArray.length; i++) {

            if(romanMap.get(romanArray[i])>temp) {
                decimalEq=(decimalEq-temp)+(romanMap.get(romanArray[i])-temp);
            }
            else {
                decimalEq+=romanMap.get(romanArray[i]);
            }
            temp=romanMap.get(romanArray[i]);
        }

        System.out.println(decimalEq);

    }


}
