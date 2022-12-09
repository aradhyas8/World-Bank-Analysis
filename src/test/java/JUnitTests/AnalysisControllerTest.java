package JUnitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import AnalysisModule.AnalysisData;
import ControllerModule.AnalysisController;
import ViewerModule.IViewer;

public class AnalysisControllerTest {
	
	AnalysisController testee;
	
	@Before
	public void setUp() {
		testee = AnalysisController.getInstance();
	}
	
	@Test
	public void testAnalysis() {
		
		String country = "Canada";
		String analysis = "Average Forest Area";
		int startYear = 2004;
		int endYear = 2010;
		ArrayList<String> views = new ArrayList<>();
		views.add("Pie Chart");
		views.add("Report");
		
		testee.requestAnalysis(analysis, views, country, startYear, endYear);
		
		AnalysisData data = testee.getAnalysisData();
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		ArrayList<IViewer> viewers = testee.getViewers();
		
		// Size should be 2 since we added 2 viewers
		assertEquals(2, viewers.size());
		// Start year of AnalysisData should be same as the start year we passed to the controller
		assertEquals(startYear, data.getStartYear());
		// End year of AnalysisData should be same as the end year we passed to the controller
		assertEquals(endYear, data.getEndYear());
		// Country of AnalysisData should be same as what we passed to the controller
		assertEquals(country, data.getCountry());
		// Title of AnalysisData should correspond to the analysis we passed to the controller. Indicating if correct analysis was done
		assertEquals("Average Forest area (% of land area)", data.getTitle());
		// dataHashMap contains 2 values that are used to create the pie chart
		assertEquals(2, dataHashMap.size());
		
	}
	
	@Test
	public void testAnalysisDifferentViewers() {
		
		String country = "Canada";
		String analysis = "Population Density Vs GDP Growth";
		int startYear = 2008;
		int endYear = 2014;
		ArrayList<String> views = new ArrayList<>();
		views.add("Line Graph");
		views.add("Report");
		views.add("Bar Graph");
		
		testee.requestAnalysis(analysis, views, country, startYear, endYear);
		
		AnalysisData data = testee.getAnalysisData();
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		ArrayList<IViewer> viewers = testee.getViewers();
		
		assertEquals(3, viewers.size());
		assertEquals(startYear, data.getStartYear());
		assertEquals(endYear, data.getEndYear());
		assertEquals(country, data.getCountry());
		assertEquals(analysis, data.getTitle());
		// dataHashMap contains 2 entries since this is a 2 series graph
		assertEquals(2, dataHashMap.size());
		
	}

}
