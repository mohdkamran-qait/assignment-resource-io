package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import java.util.Properties;
import java.io.*;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {

	public Object getOptionValue(String optionKey) throws IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		Object optionvalue = null;

		try {
			InputStream input = new FileInputStream(
					"C:\\Users\\mohdkamran\\Documents\\assignment-resource-io-master\\src\\main\\resources\\options.properties");

			Properties option = new Properties();

			// loading the properties file
			option.load(input);

			// getting the option value from Property file
			optionvalue = option.getProperty(optionKey);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return optionvalue;
	}

	public void addOption(String optionKey, Object optionValue) throws IOException {
		// throw new UnsupportedOperationException("Not implemented.");
		File file;
		InputStream input = null;
		FileOutputStream output = null;
		file = new File(
				"C:\\Users\\mohdkamran\\Documents\\assignment-resource-io-master\\src\\main\\resources\\options.properties");
		Properties option = new Properties();

		String optionKey1 = "ResourceIOTest";
		String optionValue1 = "";

		String optionKey2 = "TestName";
		String optionValue2 = "";

		String optionKey3 = "TimeStamp";
		String OptionValue3 = "";

		String obj = null;

		try {
			input = new FileInputStream(
					"C:\\Users\\mohdkamran\\Documents\\assignment-resource-io-master\\src\\main\\resources\\options.properties");
			option.load(input);

			output = new FileOutputStream(file);

			option.load(new FileInputStream(
					"C:\\Users\\mohdkamran\\Documents\\assignment-resource-io-master\\src\\main\\resources\\options.properties"));

			optionValue1 = option.getProperty(optionKey1);
			optionValue2 = option.getProperty(optionKey2);

			option.setProperty(optionKey1, optionValue1);
			option.setProperty(optionKey2, optionValue2);
			option.setProperty(optionKey3, optionValue.toString());

			option.store(output, null);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			output.close();
		}

	}
}
