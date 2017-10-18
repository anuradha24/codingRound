package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class FileUtils {
	private static final String DATA_FILE_PATH = "src/main/java/data/";
	
	public static Object[][] getDataFromCsvFile(String fileName,String delimiter){
		 BufferedReader br = null;
	     String line = "";
	     ArrayList<String> dataList = new ArrayList<>();
	        try {

	            br = new BufferedReader(new FileReader(DATA_FILE_PATH+fileName));
	            while ((line = br.readLine()) != null) {
	            	dataList.add(line);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	   int numData = dataList.size();
	   Object[][] dataValues = new Object[numData][];
	   for(int i=0;i<numData;i++){
		   dataValues[i] = dataList.get(i).split(delimiter);
	   }
	   return dataValues;
}
	
	public static Properties getPropertiesFromFile(String fileName){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(DATA_FILE_PATH+fileName);
			prop.load(input);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	}
