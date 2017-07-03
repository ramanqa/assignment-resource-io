package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public Object getOptionValue(String optionKey) throws IOException {
    	
    	Properties prop = new Properties();
    	InputStream input = null;
    	Object obj =null;
try{
    	input = new FileInputStream("src/main/resources/options.properties");

    		// load a properties file
    		prop.load(input);

    		// get the property value and print it out
    		obj=prop.getProperty(optionKey);
    	

}

catch(IOException e){
	System.out.println(e);
	
}

return obj;

       // throw new UnsupportedOperationException("Not implemented.");
    }

    public Object addOption(String optionKey, Object optionValue) throws IOException {
    	
    	Properties prop=new Properties();
    	OutputStream output=null;
    	Object obj=null;
   
    	try{
    	output = new FileOutputStream("src/main/resources/options.properties",true);
    	
  
    	obj=prop.setProperty(optionKey,(String) optionValue);
    
    	prop.store(output,null);
    	
    	}

catch(IOException e){
	System.out.println(e);
	
}
    	return obj;
    	
    
    	
      //  throw new UnsupportedOperationException("Not implemented.");
    }
}
