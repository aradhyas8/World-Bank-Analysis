package ControllerModule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParamValidator implements IParamValidator {
	
	@Override
	public boolean validateCountry(String country) {
		
		if(country == null || country.isEmpty()) {
			return false;
		}
		
		JSONParser jsonParser = new JSONParser();
        
		// Contries.json contains a list of countries for which data fetching IS ALLOWED
        try (FileReader reader = new FileReader("Countries.json")) {
        	
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
           
           // If the country exists in the list of countries in the JSON file then return true
           return jsonObject.containsKey(country);
    		
        	
        } catch (FileNotFoundException e) {
	
			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
        return false;
	}
	
	@Override
	public boolean validateYearRangeForAnalysis(String analysis, int startYear, int endYear) {
		
		if(analysis == null || analysis.isEmpty()) {
			return false;
		}
		
		if(validateYearRange(startYear, endYear) == false) {
			return false;
		}
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("InvalidYears.json"))  {
			
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray invalidYearsArray = (JSONArray) jsonObject.get(analysis);
            
            HashSet<Long> invalidYearsSet = new HashSet<Long>();
            
            @SuppressWarnings("unchecked")
			Iterator<Long> iterator = invalidYearsArray.iterator();
			
    		while (iterator.hasNext()) {	
    			invalidYearsSet.add(iterator.next());
    		}
    		
    		for(long i = startYear; i <= endYear; i++) {
    			
    			if(invalidYearsSet.contains(i)) {
    				return false;
    			}
    		}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	public boolean validateViewer(String analysis, String view) {
		
		if(analysis == null || view == null || analysis.isEmpty() || view.isEmpty()) {
			return false;
		}
		
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("InvalidViewers.json"))  {
			
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray invalidViewers = (JSONArray) jsonObject.get(analysis);
            
            HashSet<String> invalidViewersSet = new HashSet<String>();
            
            @SuppressWarnings("unchecked")
			Iterator<String> iterator = invalidViewers.iterator();
			
    		while (iterator.hasNext()) {	
    			invalidViewersSet.add(iterator.next());
    		}
    			
			if(invalidViewersSet.contains(view)) {
				return false;
			}	
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return true;
		
	}

	@Override
	public boolean validateYearRange(int startYear, int endYear) {
		if(startYear < 1985 || endYear < 1986 || endYear > 2021 || endYear - startYear <= 0) {
			return false;
		}
		return true;
	}

}
