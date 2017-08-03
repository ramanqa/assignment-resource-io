package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public Object getOptionValue(String optionKey) throws IOException {
     
    	
    	Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("C:/Users/priyanka.sharma/Desktop/assignment-resource-io/src/main/resources/options.properties");

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        String s = prop.getProperty(optionKey);
        return s;
        
         
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
      
    	
    	FileWriter fw = new FileWriter(new File("C:/Users/priyanka.sharma/Desktop/assignment-resource-io/src/main/resources/options.properties"),true);
        Properties p=new Properties();  
        String s = optionValue.toString();
          p.setProperty(optionKey,s);    
        p.store(fw,null);
        fw.close();
    }
}
