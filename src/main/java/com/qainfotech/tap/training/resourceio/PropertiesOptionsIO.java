package com.qainfotech.tap.training.resourceio;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {

	/**
	 * 
	 * @param optionKey String
	 * @return Object
	 */
	public Object getOptionValue(String optionKey) {


		BufferedReader b = null;

		try {

			File f = new File("src/main/resources/options.properties");

			b = new BufferedReader(new FileReader(f));

			String readLine = "";boolean flag = false;

			String arrsplit[],temp[] = null;
			while ((readLine = b.readLine()) != null) {
				arrsplit = readLine.split("=");

				if (arrsplit[0].equals(optionKey)) {
					flag=true;
					temp=readLine.split("=");
				}
		
			}
			
			if(flag==true)
			{
				
				return temp[1];
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;

		// throw new UnsupportedOperationException("Not implemented.");
	}

	public void addOption(String optionKey, Object optionValue) throws IOException {

		File f = new File("src/main/resources/options.properties");

		String readLine = "", olddata = "";
		BufferedReader b = new BufferedReader(new FileReader(f));
		while ((readLine = b.readLine()) != null) {

			if (olddata == "") {
				olddata = olddata + readLine;
			} else {
				olddata = olddata + "\n" + readLine;

			}
		}

		olddata = olddata + "\n";

		try {
			FileWriter fw = new FileWriter("src/main/resources/options.properties");
			fw.append(olddata + optionKey + "=" + optionValue.toString());
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.close();


		// throw new UnsupportedOperationException("Not implemented.");
	}
	
public static void main(String... S) throws IOException, ObjectNotFoundException {

		//PropertiesOptionsIO obj = new PropertiesOptionsIO();
		//obj.getOptionValue("ResourceIOTest");
		//obj.addOption("akshay","good boy");
		
		
	}
}
