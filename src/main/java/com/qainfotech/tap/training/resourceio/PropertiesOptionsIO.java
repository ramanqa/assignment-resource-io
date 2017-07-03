package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    Properties prop = new Properties();
    public Object getOptionValue(String optionKey) throws IOException {
         	InputStream input = null;
    	input = getClass().getClassLoader().getResourceAsStream("options.properties");  
    	prop.load(input);
    	 Object optionvalue=prop.getProperty(optionKey);
    	 input.close();
         return optionvalue;
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
       	OutputStream out = new FileOutputStream("C:\\Users\\purviagarwal\\Downloads\\assignment-resource-io-master\\src\\main\\resources\\options.properties");
    	 
       prop.setProperty(optionKey,optionValue.toString());
       prop.store(out, null);
         out.close();
    }
}
