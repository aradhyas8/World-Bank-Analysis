package DataFetcherModule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataFetcher implements IDataFetcher {
	
	@Override
	public HashMap<Integer, Double> getData(String country, String indicator, int startYear, int endYear) {
		
		if(country == null || indicator == null || country.isEmpty() || indicator.isEmpty()) {
			// return null; cannot call API with invalid query params
			return null;
		}
		
		HashMap<Integer, Double> dataMap = new HashMap<Integer, Double>();
		
		String isoCode = getISOCode(country);
		
		String urlString = 
				String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", 
						isoCode, indicator, startYear, endYear);
		
		try {
			
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			
			// If the response is 200, then save the results to a string
			if (responsecode == 200) {
				
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				
				sc.close();
				
				JSONParser jsonParser = new JSONParser();
				
				// Parse the results string into an object using a JSON parser
				Object object = (Object) jsonParser.parse(inline);
				
				// JSON object returned by API is an array so convert to JSONArray
				JSONArray jsonArray = (JSONArray) object;
				
				// Get the second element in the array as an array. This contains all the required info
				JSONArray resultsArray = (JSONArray) jsonArray.get(1);
				
				int year = 0;
				double value = 0.0;
				String yearString;
				
				// Loop through the JSON array and save the year and value in the HashMap
				for (int i = 0; i < resultsArray.size(); i++) {
				
					JSONObject result = (JSONObject) resultsArray.get(i);
					
					yearString = (String) result.get("date");
					year = Integer.parseInt(yearString);
					value = (double) result.get("value");
					
					dataMap.put(year,value);
				}	
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ParseException e) {
	
			e.printStackTrace();
		}

		return dataMap;
		
	}
	
	/**
	 * <p> Helper method to get the ISO3 code of a country from a JSON file
	 * </p>
	 * @param country
	 * @return ISO3 code of the given country
	 */
	private String getISOCode(String country) {
		
		String isoCode = "";
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("Countries.json")) {
        	
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
           
            isoCode += (String) jsonObject.get(country);
    		
        	
        } catch (FileNotFoundException e) {
	
			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
        
        return isoCode;
		
	}

}
