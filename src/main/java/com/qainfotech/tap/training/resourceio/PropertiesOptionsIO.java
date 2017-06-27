package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import java.io.FileInputStream;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public Object getOptionValue(String optionKey) throws IOException {
     
		InputStream input = new FileInputStream("src/main/resources/options.properties");
		Object obj = null;
        prop.load(input);
		obj = prop.getProperty(optionKey);
          return obj;
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
     
        InputStream input = new FileInputStream("src/main/resources/options.properties");
		prop.load(input);
		input.close();
		OutputStream output=null;
		output = new FileOutputStream("src/main/resources/options.properties");
		
		prop.setProperty(optionKey,optionValue.toString());
		prop.store(output, null);
    }
}
