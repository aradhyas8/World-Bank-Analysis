package JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

import AnalysisModule.AirPollutionVsForestArea;
import AnalysisModule.AnalysisData;
import AnalysisModule.AnalysisStrategy;
import AnalysisModule.AvgForestArea;
import AnalysisModule.CO2VsEnergyVsAirPollution;
import AnalysisModule.CO2VsGDP;
import AnalysisModule.EduExpenditure;
import AnalysisModule.EduVsHealthExpen;
import AnalysisModule.HealthExpenVsHospitalBeds;
import AnalysisModule.PopulationDensityVsGDPGrowth;

public class AnalysisStrategyTest {
	
	private AnalysisStrategy strategy;
	
	@Test
	public void testPopulationDensityVsGDPGrowth() {
		
		strategy = new PopulationDensityVsGDPGrowth();
		AnalysisData data = strategy.doAnalysis("Canada", 2006, 2012);
		
		int rangeLength = 2012 - 2006 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Canada", data.getCountry());
		assertEquals("Population Density Vs GDP Growth", data.getTitle());
		assertEquals(2, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("GDP Growth"));
		assertTrue(dataHashMap.containsKey("Population Density"));
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}
	
	@Test
	public void testAvgForestArea() {
		
		strategy = new AvgForestArea();
		AnalysisData data = strategy.doAnalysis("Canada", 2006, 2012);
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Canada", data.getCountry());
		assertEquals("Average Forest area (% of land area)", data.getTitle());
		assertEquals(2, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("Average Forest Area"));
		assertTrue(dataHashMap.containsKey("All Other Land"));
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
		    // only expecting 1 value in the array since it is the average for all the years 
			assertEquals(1, values.length);
			
		}
	}
	
	@Test
	public void testEduExpenditure() {
		
		strategy = new EduExpenditure();
		AnalysisData data = strategy.doAnalysis("Mexico", 2002, 2009);
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Mexico", data.getCountry());
		assertEquals("Average Government Expenditure on Education (% of GDP)", data.getTitle());
		assertEquals(2, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("Average Education Expensiture"));
		assertTrue(dataHashMap.containsKey("All Other Expenditures"));
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
		    // only expecting 1 value in the array since it is the average for all the years 
			assertEquals(1, values.length);
			
		}
	}
	
	@Test
	public void testAirPollutionVsForestArea() {
		
		strategy = new AirPollutionVsForestArea();
		AnalysisData data = strategy.doAnalysis("Mexico", 2011, 2017);
		
		int rangeLength = 2017 - 2011 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Mexico", data.getCountry());
		assertEquals("Air Pollution Vs Forest Area", data.getTitle());
		assertEquals(2, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("PM 2.5 Air Pollution"));
		assertTrue(dataHashMap.containsKey("Forest Area"));
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}
	
	@Test
	public void testCO2VsGDP() {
		
		strategy = new CO2VsGDP();
		AnalysisData data = strategy.doAnalysis("United States", 2006, 2012);
		
		int rangeLength = 2012 - 2006 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("United States", data.getCountry());
		assertEquals("CO2 vs GDP (ratio)", data.getTitle());
		// 1 series graph so 1 dataset entry
		assertEquals(1, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("Ratio"));
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}
	
	@Test
	public void testCO2VsEnergyVsAirPollution() {
		
		strategy = new CO2VsEnergyVsAirPollution();
		AnalysisData data = strategy.doAnalysis("Brazil", 2011, 2014);
		
		int rangeLength = 2014 - 2011 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Brazil", data.getCountry());
		assertEquals("CO2 EMissions vs Energy Use & Air Pollution", data.getTitle());
		// 3 series graph so 3 dataset entries
		assertEquals(3, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("CO2 emissions (as metric tons per capita)"));
		assertTrue(dataHashMap.containsKey("Energy use (as kg of oil equivalent per capita)"));
		assertTrue(dataHashMap.containsKey("PM2.5 air pollution, mean annual exposure (as micrograms per cubic meter)"));
		
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}
	
	@Test
	public void testEduVsHealthExpen() {
		
		strategy = new EduVsHealthExpen();
		AnalysisData data = strategy.doAnalysis("Mexico", 2003, 2009);
		
		int rangeLength = 2009 - 2003 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Mexico", data.getCountry());
		assertEquals("Government Expenditure Vs Health Expenditure", data.getTitle());
		// 2 series graph so 2 dataset entries
		assertEquals(2, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("Government expenditure on education, total (% of GDP)"));
		assertTrue(dataHashMap.containsKey("Current health expenditure (% of GDP)"));
		
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}
	
	@Test
	public void testHealthExpenVsHospitalBeds() {
		
		strategy = new HealthExpenVsHospitalBeds();
		AnalysisData data = strategy.doAnalysis("Canada", 2001, 2008);
		
		int rangeLength = 2008 - 2001 + 1;
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		assertEquals("Canada", data.getCountry());
		assertEquals("Current health expenditure Vs Hospital beds", data.getTitle());
		// 1 series graph so 1 dataset entry
		assertEquals(1, dataHashMap.size());
	
		assertTrue(dataHashMap.containsKey("Ratio"));
		
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		  
		    double[] values = entry.getValue();	
			assertEquals(rangeLength, values.length);
			
		}
	}

}
