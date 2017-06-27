package com.qainfotech.tap.training.resourceio;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {

	public Object getOptionValue(String optionKey) throws IOException {

		Properties prop = new Properties();
		InputStream input = null;
		Object obj = null;

		File file;
		try {

			input = new FileInputStream("src/main/resources/options.properties");
			prop.load(input);
			obj = prop.getProperty(optionKey);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return obj;

	}

	public void addOption(String optionKey, Object optionValue) throws IOException {

		Properties prop = new Properties();
		FileOutputStream output = null;
		File file, filecopy;

		String optionKey1 = "ResourceIOTest";
		String optionValue1 = "";

		String optionKey2 = "TestName";
		String OptionValue2 = "";

		String optionKey3 = "TimeStamp";
		String OptionValue3 = "";

		InputStream input = null;
		String obj = null;

		try {

			file = new File("src/main/resources/options.properties");
			filecopy = file;

			input = new FileInputStream("src/main/resources/options.properties");

			prop.load(input);

			output = new FileOutputStream(file);

			prop.load(new FileInputStream("src/main/resources/options.properties"));

			optionValue1 = prop.getProperty(optionKey1);
			OptionValue2 = prop.getProperty(optionKey2);

			prop.setProperty(optionKey1, optionValue1);
			prop.setProperty(optionKey2, OptionValue2);
			prop.setProperty(optionKey, optionValue.toString());

			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();

		} finally {
			output.close();
		}
	}
}
