package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import java.util.Properties;
import java.io.*;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */

public class PropertiesOptionsIO {

	/**
	 * 
	 * @param optionKey
	 * @return optionvalue
	 * @throws IOException
	 */

	public Object getOptionValue(String optionKey) throws IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		Object optionvalue = null;

		try {
			
			Properties option = new Properties()
			ClassLoader classLoader = getClass().getClassLoader();
			java.net.URL resource = classLoader.getResource("options.properties");
			
			// loading the properties file
			option.load(new FileReader(new File(resource.getFile())));

			// getting the option value from Property file
			optionvalue = option.getProperty(optionKey);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return optionvalue;
	}

	/**
	 * 
	 * @param optionKey
	 * @param optionValue
	 * @throws IOException
	 */

	public void addOption(String optionKey, Object optionValue) throws IOException {

		Properties option = new Properties();
		FileOutputStream output = null;
		File file;

		String optionKey1 = "ResourceIOTest";
		String optionValue1 = "";

		String optionKey2 = "TestName";
		String optionValue2 = "";

		String optionKey3 = "TimeStamp";
		String optionValue3 = "";

		InputStream input = null;

		try {

			file = new File("src/main/resources/options.properties");
			input = new FileInputStream("src/main/resources/options.properties");

			// loading resource file
			option.load(input);

			// getting the property value
			output = new FileOutputStream(file);

			option.load(new FileInputStream("src/main/resources/options.properties"));
			// setting the properties value

			optionValue1 = option.getProperty(optionKey1);
			optionValue2 = option.getProperty(optionKey2);

			option.setProperty(optionKey1, optionValue1);
			option.setProperty(optionKey2, optionValue2);
			option.setProperty(optionKey, optionValue.toString());

			// saving the properties
			option.store(output, null);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			output.close();
		}
	}
}
