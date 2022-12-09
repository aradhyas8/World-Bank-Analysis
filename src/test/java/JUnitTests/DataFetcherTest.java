package JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class DataFetcherTest {
	
	IDataFetcher testee = new DataFetcher();
	
	@Test
	public void testFetchData() {
		
		String country = "Canada";
		String indicator = "AG.LND.FRST.ZS";
		int startYear = 2004;
		int endYear = 2015;
		
		HashMap<Integer, Double> resultMap = testee.getData(country, indicator, startYear, endYear);
		
		assertNotNull(resultMap);
		
		int size = endYear - startYear + 1;
		
		// HashMap length should contain an entry for each year in the range
		assertEquals(size, resultMap.size());
		
		Set<Integer> keySet = resultMap.keySet();
		
		// Check whether all years from start year to end year are in the HashMap
		
		for(int i = startYear; i <= endYear; i++) {
			assertTrue(keySet.contains(startYear));
		}
		
		// Check the value for the year is same as API response
		double val = resultMap.get(2008);
		
		// Value in the HashMap for each year should be mapped appropriately 
		assertEquals(38.7501728274436, val, 0.0001);
	}
	
	@Test
	public void testNullCountry() {
	
		String indicator = "AG.LND.FRST.ZS";
		int startYear = 2004;
		int endYear = 2015;
		
		HashMap<Integer, Double> resultMap = testee.getData(null, indicator, startYear, endYear);
		
		assertNull(resultMap);	
	}
	
	@Test
	public void testNullIndicator() {
	
		String country = "Canada";
		int startYear = 2004;
		int endYear = 2015;
		
		HashMap<Integer, Double> resultMap = testee.getData(country, null, startYear, endYear);
		
		assertNull(resultMap);
	}

}
