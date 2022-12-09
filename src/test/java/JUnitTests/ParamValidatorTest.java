package JUnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ControllerModule.IParamValidator;
import ControllerModule.ParamValidator;


public class ParamValidatorTest {
	
	IParamValidator testee = new ParamValidator();
	
	@Test
	public void testValidCountry() {
		
		// Canada exists in countries.JSON and therefore data fetching should be allowed 
		String country = "Canada";
		
		boolean result = testee.validateCountry(country);
		assertTrue(result);	
	}
	
	@Test
	public void testNullCountry() {
		
		boolean result = testee.validateCountry(null);
		assertFalse(result);	
	}
	
	@Test
	public void testEmptyCountry() {
		
		boolean result = testee.validateCountry("");
		assertFalse(result);	
	}
	
	@Test
	public void testInvalidCountry() {
		
		// Afghanistan does not exists in countries.JSON and therefore data fetching should NOT allowed 
		String country = "Afghanistan";
		
		boolean result = testee.validateCountry(country);
		assertFalse(result);	
	}
	
	@Test
	public void testInvalidYearRange() {
		
		// Invalid year range as start year is larger than end year
		int startYear = 2010;
		int endYear = 2005;
		
		boolean result = testee.validateYearRange(startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testValidYearRange() {
		
		// Valid start year and end year
		int startYear = 2001;
		int endYear = 2008;
		
		boolean result = testee.validateYearRange(startYear, endYear);
		assertTrue(result);	
	}
	
	@Test
	public void testYearRangInvalidStartYear() {
		
		int startYear = 1976;
		int endYear = 2008;
		
		boolean result = testee.validateYearRange(startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testYearRangInvalidEndYear() {
		
		int startYear = 2003;
		int endYear = 1980;
		
		boolean result = testee.validateYearRange(startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testInvalidYearRangeForAnalysis() {
		
		// Start and end year is valid but data cannot be fetched for analysis AvgForestArea 
		// for the years 1985-1989 so it makes the range invalid for this analysis
		int startYear = 1985;
		int endYear = 1990;
		String analysis = "Average Forest Area";
		
		boolean result = testee.validateYearRangeForAnalysis(analysis, startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testValidYearRangeForAnalysis() {
		 
		int startYear = 2006;
		int endYear = 2016;
		String analysis = "Average Forest Area";
		
		boolean result = testee.validateYearRangeForAnalysis(analysis, startYear, endYear);
		assertTrue(result);	
	}
	
	@Test
	public void testYearRangeForNullAnalysis() {
		
		int startYear = 2004;
		int endYear = 2017;
		
		boolean result = testee.validateYearRangeForAnalysis(null, startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testYearRangeForEmptyAnalysis() {
		
		int startYear = 2004;
		int endYear = 2017;
		
		boolean result = testee.validateYearRangeForAnalysis("", startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testYearRangeForInvalidStartYear() {
		
		String analysis = "Population Density Vs GDP Growth";
		int startYear = 1981;
		int endYear = 2017;
		
		boolean result = testee.validateYearRangeForAnalysis(analysis, startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testYearRangeForInvalidEndYear() {
		
		String analysis = "Population Density Vs GDP Growth";
		int endYear = 2023;
		int startYear = 2002;
		
		boolean result = testee.validateYearRangeForAnalysis(analysis, startYear, endYear);
		assertFalse(result);	
	}
	
	@Test
	public void testValidViewer() {
		
		String analysis = "Average Forest Area";
		String viewer = "Pie Chart";
		
		boolean result = testee.validateViewer(analysis, viewer);
		assertTrue(result);	
	}
	
	@Test
	public void testInvalidViewer() {
		
		String analysis = "Average Forest Area";
		String viewer = "Bar Graph";
		
		boolean result = testee.validateViewer(analysis, viewer);
		assertFalse(result);
	}
	
	@Test
	public void testValidateViewerNullView() {
		
		String analysis = "Average Forest Area";
		
		boolean result = testee.validateViewer(analysis, null);
		assertFalse(result);
	}
	
	@Test
	public void testValidateViewerEmptyView() {
		
		String analysis = "Average Forest Area";
		
		boolean result = testee.validateViewer(analysis, "");
		assertFalse(result);
	}
	
	@Test
	public void testValidateViewerNullAnalysis() {
		
		String viewer = "Report";
		
		boolean result = testee.validateViewer(null, viewer);
		assertFalse(result);
	}
	
	@Test
	public void testValidateViewerEmptyAnalysis() {
		
		String viewer = "Report";
		
		boolean result = testee.validateViewer("", viewer);
		assertFalse(result);
	}
	

}
